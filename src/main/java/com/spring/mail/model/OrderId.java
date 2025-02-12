package com.spring.mail.model;

import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderId implements Serializable {
    private UUID orderId;

    private UUID user;
}
