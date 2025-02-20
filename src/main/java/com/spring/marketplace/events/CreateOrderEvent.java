package com.spring.marketplace.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.spring.marketplace.dto.CreateOrderDto;
import com.spring.marketplace.service.OrderService;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigInteger;
import java.util.Map;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("created")
public class CreateOrderEvent implements EventSource {

    @NotNull(message = "No products sku or their quantity were transferred")
    private Map<String, BigInteger> productMap;

    @NotNull(message = "Id must be not null")
    private UUID userId;

    private Event event = Event.CREATE_ORDER;

    @Override
    public void handleEvent(OrderService service) {
        CreateOrderDto createOrderDto = CreateOrderDto.builder()
                .productMap(this.getProductMap())
                .build();

        service.createOrder(createOrderDto, this.getUserId());
    }
}
