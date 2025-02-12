package com.spring.mail.dto;


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
public class CreateOrderDto {

    @NotNull(message = "No products sku or their quantity were transferred")
    private Map<String, BigInteger> productMap;
}
