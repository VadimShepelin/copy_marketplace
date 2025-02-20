package com.spring.marketplace.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.spring.marketplace.dto.UpdateOrderStateDto;
import com.spring.marketplace.model.enums.Status;
import com.spring.marketplace.service.OrderService;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("cancelled")
public class CancelledOrderEvent implements EventSource {

    @NotNull(message = "Order id must be not null")
    private UUID orderId;

    private Event event = Event.CANCELLED_ORDER;

    @Override
    public void handleEvent(OrderService service) {
        UpdateOrderStateDto updateOrderDto = UpdateOrderStateDto.builder()
                .status(Status.CANCELLED)
                .build();

        service.updateOrderState(updateOrderDto, this.getOrderId());
    }
}
