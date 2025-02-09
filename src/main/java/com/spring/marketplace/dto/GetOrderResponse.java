package com.spring.marketplace.dto;

import com.spring.marketplace.model.enums.Status;
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
