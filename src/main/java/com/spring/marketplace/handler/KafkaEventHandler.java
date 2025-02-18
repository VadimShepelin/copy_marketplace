package com.spring.marketplace.handler;

import com.spring.marketplace.dto.CreateOrderDto;
import com.spring.marketplace.dto.UpdateOrderStateDto;
import com.spring.marketplace.model.enums.Status;
import com.spring.marketplace.service.OrderService;
import com.spring.source.events.CancelledOrderEvent;
import com.spring.source.events.CompletedOrderEvent;
import com.spring.source.events.CreateOrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = {"events"}, groupId = "${spring.kafka.consumer.group-id}")
@Slf4j
@RequiredArgsConstructor
public class KafkaEventHandler {

    private final OrderService orderService;

    @KafkaHandler
    public void handleCancelledOrderEvent(CancelledOrderEvent event){
        log.info("call handleCancelledOrderEvent method with event: {}", event.getEvent());

        UpdateOrderStateDto updateOrderDto = UpdateOrderStateDto.builder()
                .status(Status.CANCELLED)
                .build();

        orderService.updateOrderState(updateOrderDto, event.getOrderId());
    }

    @KafkaHandler
    public void handleCompletedOrderEvent(CompletedOrderEvent event){
        log.info("call handleCompletedOrderEvent method with event: {}",event.getEvent());

        UpdateOrderStateDto completedOrderDto = UpdateOrderStateDto.builder()
                .status(Status.DONE)
                .build();

        orderService.updateOrderState(completedOrderDto,event.getOrderId());
    }

    @KafkaHandler
    public void handleCreateOrderEvent(CreateOrderEvent event){
        log.info("call handleCreateOrderEvent method with event: {}",event.getEvent());

        CreateOrderDto createOrderDto = CreateOrderDto.builder()
                .productMap(event.getProductMap())
                .build();

        orderService.createOrder(createOrderDto, event.getUserId());
    }
}
