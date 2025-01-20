package com.spring.marketplace.dto;


import com.spring.marketplace.model.Categories;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
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
public class GetProductResponse {

    private String name;

    private String description;

    private Categories category;

    private BigDecimal price;

    private BigInteger quantity;

    private String sku;

    private LocalDateTime createdAt;

    private LocalDateTime updated_at;
}
