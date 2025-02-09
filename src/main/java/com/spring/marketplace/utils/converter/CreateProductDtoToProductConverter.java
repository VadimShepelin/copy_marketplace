package com.spring.marketplace.utils.converter;


import com.spring.marketplace.dto.CreateProductDto;
import com.spring.marketplace.model.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class CreateProductDtoToProductConverter implements Converter<CreateProductDto, Product> {

    @Override
    public Product convert(CreateProductDto productDto) {
        return Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .sku(productDto.getSku())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .quantity(productDto.getQuantity())
                .category(productDto.getCategory())
                .isAvailable(productDto.getIsAvailable()==null? true : productDto.getIsAvailable())
                .build();
    }


}
