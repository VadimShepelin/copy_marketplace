package com.spring.marketplace.controller;

import com.spring.marketplace.dto.CreateProductDto;
import com.spring.marketplace.dto.GetProductResponse;
import com.spring.marketplace.dto.ProductFilterDto;
import com.spring.marketplace.dto.UpdateProductDto;
import com.spring.marketplace.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<GetProductResponse> getAllProducts(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return productService.getAllProducts(pageNo,pageSize);
    }

    @GetMapping("/{id}")
    public GetProductResponse getProductById(@PathVariable UUID id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product was deleted");
    }

    @PostMapping
    public GetProductResponse createProduct(@Valid @RequestBody CreateProductDto productDto) {
       return productService.saveProduct(productDto);
    }

    @PutMapping
    public GetProductResponse updateProduct(@Valid @RequestBody UpdateProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @GetMapping("/search")
    public List<GetProductResponse> searchProductsWithFilter(@RequestBody ProductFilterDto productDto){
        return productService.searchProductsWithFilter(productDto);
    }

}
