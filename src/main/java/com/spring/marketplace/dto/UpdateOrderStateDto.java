package com.spring.marketplace.dto;

import com.spring.marketplace.model.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Schema(description = "Dto to update order status")
public class UpdateOrderStateDto {

    @NotNull(message = "Status is not correct")
    @Schema(description = "Order status", allowableValues = {"CANCELLED", "APPROVED", "DONE", "REJECTED","CREATED"})
    private Status status;
}
