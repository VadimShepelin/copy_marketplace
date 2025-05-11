package com.spring.marketplace.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.AccessLevel;
import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Schema(description = "Dto to get products with filter")
public class ProductFilterDto {

    @Schema(description = "Product name", example = "Organic Apple")
    private String name;

    @Schema(description = "Quantity filter", example = "100")
    private BigInteger quantity;

    @Schema(description = "Price filter", example = "200")
    private BigDecimal price;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Boolean isAvailable;
}
