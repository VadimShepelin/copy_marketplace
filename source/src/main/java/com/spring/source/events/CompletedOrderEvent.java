package com.spring.source.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@NotNull
@Setter
@Getter
@JsonTypeName("completed")
public class CompletedOrderEvent implements EventSource {

    @NotNull(message = "Order id must be not null")
    private UUID orderId;

    private Event event = Event.COMPLETED_ORDER;
}
