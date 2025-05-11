package com.spring.marketplace.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Builder;
import lombok.Setter;
import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@Schema(description = "Dto to get product sku and quantity")
public class ProductSkuAndQuantityDto {

    @Schema(description = "Product sku", example = "FOOD-APP-001")
    private String sku;

    @Schema(description = "Products quantity", example = "15")
    private BigInteger quantity;
}
