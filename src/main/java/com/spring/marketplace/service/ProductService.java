package com.spring.marketplace.service;

import com.spring.marketplace.dto.CreateProductDto;
import com.spring.marketplace.dto.GetProductResponse;
import com.spring.marketplace.dto.ProductFilterDto;
import com.spring.marketplace.dto.UpdateProductDto;
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
