package com.spring.mail.utils.converter;

import com.spring.mail.dto.GetProductResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToReadProductDtoConverter implements Converter<com.spring.mail.model.Product, GetProductResponse> {

    @Override
    public GetProductResponse convert(com.spring.mail.model.Product product) {
        return GetProductResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .category(product.getCategory())
                .sku(product.getSku())
                .updated_at(product.getUpdatedAt())
                .build();
    }
}
