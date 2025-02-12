package com.spring.mail.util;

import com.spring.mail.dto.CreateProductDto;
import com.spring.mail.dto.GetProductResponse;
import com.spring.mail.dto.UpdateProductDto;
import com.spring.mail.model.enums.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public class ProductTestUtil {

    private static final String URL = "/api/v1/products";
    private static final String ID = "e1f47ac2-5b6a-42fb-9a41-8f0f9f6539d1";

    public static CreateProductDto createProductDto() {
        return CreateProductDto.builder()
                .category(Categories.CLOTH)
                .price(BigDecimal.valueOf(1000))
                .quantity(BigInteger.valueOf(150))
                .name("NEW PRODUCT FOR TEST")
                .description("NEW DESCRIPTION FOR PRODUCT")
                .sku("TEST SKU")
                .build();
    }

    public static CreateProductDto createProductDtoWithNullSku(){
        return CreateProductDto.builder()
                .category(Categories.CLOTH)
                .price(BigDecimal.valueOf(1000))
                .quantity(BigInteger.valueOf(150))
                .name("NEW PRODUCT FOR TEST")
                .description("NEW DESCRIPTION FOR PRODUCT")
                .sku(null)
                .build();
    }

    public static UpdateProductDto createUpdateProductDto() {
        return UpdateProductDto.builder()
                .sku("FOOD-APP-001")
                .name("Updated Name")
                .description("Updated Description")
                .quantity(BigInteger.valueOf(150))
                .price(BigDecimal.valueOf(199.99))
                .category(Categories.CLOTH)
                .build();
    }

    public static UpdateProductDto createUpdateProductDtoWithEmptyName() {
        return UpdateProductDto.builder()
                .sku("FOOD-APP-001")
                .name("   ")
                .description("Updated Description")
                .quantity(BigInteger.valueOf(150))
                .price(BigDecimal.valueOf(199.99))
                .category(Categories.CLOTH)
                .build();
    }

    public static com.spring.mail.model.Product createProduct() {
        return com.spring.mail.model.Product.builder()
                .id(UUID.randomUUID())
                .sku("TEST SKU")
                .build();
    }

    public static GetProductResponse createGetProductResponse() {
        return GetProductResponse.builder()
                .sku("TEST SKU")
                .build();
    }

    public static Page createPage(com.spring.mail.model.Product product){
        return new PageImpl(List.of(product));
    }

    public static String getUrl(){
        return URL;
    }

    public static String getUrlWithId(){
        return getUrl() + "/" + ID;
    }

    public static String getUrlWithIncorrectId(){
        return getUrl() + "/incorrect-id";
    }
}
