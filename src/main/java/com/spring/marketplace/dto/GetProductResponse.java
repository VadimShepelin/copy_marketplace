package com.spring.marketplace.dto;


import com.spring.marketplace.model.enums.Categories;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Builder;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@Schema(description = "Dto to get info about product")
public class GetProductResponse {

    @Schema(description = "Product name", example = "Organic Apple")
    private String name;

    @Schema(description = "Product description", example = "Fresh organic apples")
    private String description;

    @Schema(description = "Product category", allowableValues = {"FOOD", "FMCG", "CLOTH", "COSMETICS", "AUTOMOTIVE_PRODUCTS", "PET_SUPPLIES"})
    private Categories category;

    @Schema(description = "Product price", example = "200")
    private BigDecimal price;

    @Schema(description = "Product quantity", example = "50")
    private BigInteger quantity;

    @Schema(description = "Product sku", example = "FOOD-APP-001")
    private String sku;

    @Schema(description = "Time when product was created", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(description = "Time when product was updated", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;
}
