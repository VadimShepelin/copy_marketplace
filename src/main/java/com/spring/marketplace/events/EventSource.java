package com.spring.marketplace.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.spring.marketplace.service.OrderService;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "event")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CancelledOrderEvent.class, name = "cancelled"),
        @JsonSubTypes.Type(value = CompletedOrderEvent.class, name = "completed"),
        @JsonSubTypes.Type(value = CreateOrderEvent.class, name = "created")
})
@Schema(description = "Base interface for all other events")
public interface EventSource{

    void handleEvent(OrderService service);
}
