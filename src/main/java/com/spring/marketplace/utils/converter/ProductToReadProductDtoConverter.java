package com.spring.marketplace.utils.converter;

import com.spring.marketplace.dto.GetProductResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToReadProductDtoConverter implements Converter<com.spring.marketplace.model.Product, GetProductResponse> {

    @Override
    public GetProductResponse convert(com.spring.marketplace.model.Product product) {
        return GetProductResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .category(product.getCategory())
                .sku(product.getSku())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
