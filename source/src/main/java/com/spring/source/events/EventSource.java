package com.spring.source.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "event")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CancelledOrderEvent.class, name = "cancelled"),
        @JsonSubTypes.Type(value = CompletedOrderEvent.class, name = "completed"),
        @JsonSubTypes.Type(value = CreateOrderEvent.class, name = "created")
})
public interface EventSource{
}
