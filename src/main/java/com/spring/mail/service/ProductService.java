package com.spring.mail.service;

import com.spring.mail.dto.CreateProductDto;
import com.spring.mail.dto.GetProductResponse;
import com.spring.mail.dto.ProductFilterDto;
import com.spring.mail.dto.UpdateProductDto;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public interface ProductService{

    List<GetProductResponse> getAllProducts(int pageNo, int pageSize);

    GetProductResponse getProductById(UUID id);

    GetProductResponse getProductBySku(String sku);

    void increaseProductQuantity(String sku, BigInteger quantity);

    void reduceProductQuantity(String sku, BigInteger quantity);

    GetProductResponse saveProduct(CreateProductDto product);

    void deleteProduct(UUID id);

    GetProductResponse updateProduct(UpdateProductDto product);

    List<GetProductResponse> searchProductsWithFilter(ProductFilterDto productFilter);
}
