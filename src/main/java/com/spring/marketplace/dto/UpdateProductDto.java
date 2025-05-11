package com.spring.marketplace.dto;


import com.spring.marketplace.model.enums.Categories;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to update product info")
public class UpdateProductDto {

    @NotBlank(message = "Name must be not empty")
    @Schema(description = "Product name", example = "Organic Apple")
    private String name;

    @NotBlank(message = "Description must be not empty")
    @Size(min = 10, message = "Description length min 10 chars")
    @Schema(description = "Product description", example = "Fresh organic apples")
    private String description;

    @NotNull(message = "Category must be not empty")
    @Schema(description = "Product category", allowableValues = {"FOOD", "FMCG", "CLOTH", "COSMETICS", "AUTOMOTIVE_PRODUCTS", "PET_SUPPLIES"})
    private Categories category;

    @Min(value = 100, message = "Min price is 100")
    @Schema(description = "Product price", example = "200")
    private BigDecimal price;

    @Schema(description = "Product quantity", example = "50")
    private BigInteger quantity;

    @NotBlank(message = "SKU must be not empty")
    @Schema(description = "Product sku", example = "FOOD-APP-001")
    private String sku;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Boolean isAvailable;
}
