package com.spring.marketplace.dto;

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
public class ProductSkuAndQuantityDto {

    private String sku;

    private BigInteger quantity;
}
