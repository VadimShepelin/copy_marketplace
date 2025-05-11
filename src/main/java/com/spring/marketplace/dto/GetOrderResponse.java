package com.spring.marketplace.dto;

import com.spring.marketplace.model.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Dto to get info about order")
public class GetOrderResponse {

    @Schema(description = "Order totalCost", example = "500.23")
    private BigDecimal totalCost;

    @Schema(description = "Order status", allowableValues = {"CANCELLED", "APPROVED", "DONE", "REJECTED","CREATED"})
    private Status status;

    @Schema(description = "Id of the user who creating this order", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID userId;
}
