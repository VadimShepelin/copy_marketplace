package com.spring.mail.dto;

import com.spring.mail.model.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderResponse {

    private BigDecimal totalCost;

    private Status status;

    private UUID userId;
}
