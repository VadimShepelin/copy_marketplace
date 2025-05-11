package com.spring.marketplace.controller;

import com.spring.marketplace.docs.product.*;
import com.spring.marketplace.dto.*;
import com.spring.marketplace.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Tag(name = "Product Controller", description = "Used to create new products, get " +
        "existing ones and etc")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @GetAllProductsDoc
    public List<GetProductResponse> getAllProducts(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return productService.getAllProducts(pageNo,pageSize);
    }

    @GetMapping("/{id}")
    @GetProductByIdDoc
    public GetProductResponse getProductById(@PathVariable UUID id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    @DeleteProductByIdDoc
    public ResponseEntity<String> deleteProductById(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product was deleted");
    }

    @PostMapping
    @CreateProductDoc
    @ResponseStatus(HttpStatus.CREATED)
    public GetProductResponse createProduct(@Valid @RequestBody CreateProductDto productDto) {
       return productService.saveProduct(productDto);
    }

    @PutMapping
    @UpdateProductDoc
    public GetProductResponse updateProduct(@Valid @RequestBody UpdateProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @GetMapping("/search")
    @SearchProductsWithFilterDoc
    public List<GetProductResponse> searchProductsWithFilter(@Valid @RequestBody @Parameter(hidden = true) ProductFilterDto productDto){
        return productService.searchProductsWithFilter(productDto);
    }

}
