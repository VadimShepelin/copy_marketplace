package com.spring.source.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
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
}
