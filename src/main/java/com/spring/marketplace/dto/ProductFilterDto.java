package com.spring.marketplace.dto;


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
public class ProductFilterDto {
    private String name;

    private BigInteger quantity;

    private BigDecimal price;

    private Boolean isAvailable;
}
