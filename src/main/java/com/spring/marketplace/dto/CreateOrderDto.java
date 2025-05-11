package com.spring.marketplace.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigInteger;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to creating orders")
public class CreateOrderDto {

    @Schema(description = "User's shopping cart", example = """
            {
                "productMap": {
                    "PET-BWL-004" : 4,
                    "PET-COL-006" : 3
                }
            }   
            """)
    @NotNull(message = "No products sku or their quantity were transferred")
    private Map<String, BigInteger> productMap;
}
