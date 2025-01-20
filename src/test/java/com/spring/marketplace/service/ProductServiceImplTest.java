package com.spring.marketplace.service;

import com.spring.marketplace.dto.CreateProductDto;
import com.spring.marketplace.dto.GetProductResponse;
import com.spring.marketplace.dto.UpdateProductDto;
import com.spring.marketplace.exception.ApplicationException;
import com.spring.marketplace.model.Product;
import com.spring.marketplace.repository.ProductRepository;
import com.spring.marketplace.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Optional;

import static com.spring.marketplace.util.ProductTestUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ConversionService conversionService;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    @DisplayName("Find product by id successfully")
    void getProductById_shouldReturnProduct() {
        Product product = createProduct();
        GetProductResponse getProductResponse = createGetProductResponse();

        doReturn(Optional.of(product)).when(productRepository).findById(product.getId());
        doReturn(getProductResponse).when(conversionService).convert(product, GetProductResponse.class);

        GetProductResponse actualResult = productService.getProductById(product.getId());

        assertNotNull(actualResult);
        assertEquals(getProductResponse, actualResult);
    }

    @Test
    @DisplayName("Get all products list successfully")
    void getAllProducts_shouldReturnProductsList() {
        Product product = createProduct();
        GetProductResponse getProductResponse = createGetProductResponse();
        Page page = createPage(product);

        doReturn(page).when(productRepository).findAll(any(PageRequest.class));
        doReturn(getProductResponse).when(conversionService).convert(product, GetProductResponse.class);

        List<GetProductResponse> actualResult = productService.getAllProducts(0, 12);

        assertNotNull(actualResult);
        assertEquals(actualResult, List.of(getProductResponse));
    }

    @Test
    @DisplayName("Successfully delete the product")
    void deleteProduct_shouldDeleteProduct() {
        Product product = createProduct();

        doReturn(Optional.of(product)).when(productRepository).findById(product.getId());
        doNothing().when(productRepository).deleteById(product.getId());

        productService.deleteProduct(product.getId());

        verify(productRepository, times(1)).deleteById(product.getId());
    }

    @Test
    @DisplayName("Successfully save the product")
    void saveProduct_shouldSaveProduct() {
        CreateProductDto productDto = createProductDto();
        Product product = createProduct();
        GetProductResponse getProductResponse = createGetProductResponse();

        doReturn(Optional.empty()).when(productRepository).findBySku(productDto.getSku());
        doReturn(product).when(conversionService).convert(productDto,Product.class);
        doReturn(product).when(productRepository).save(product);
        doReturn(getProductResponse).when(conversionService).convert(product,GetProductResponse.class);

        GetProductResponse actualResult = productService.saveProduct(productDto);

        assertNotNull(actualResult);
        assertEquals(getProductResponse, actualResult);
    }

    @Test
    @DisplayName("Successfully updated the product")
    void updateProduct_shouldUpdateProduct() {
        UpdateProductDto productDto = createUpdateProductDto();
        Product product = createProduct();
        GetProductResponse getProductResponse = createGetProductResponse();

        doReturn(Optional.of(product)).when(productRepository).findBySku(productDto.getSku());
        doReturn(product).when(productRepository).save(product);
        doReturn(getProductResponse).when(conversionService).convert(product,GetProductResponse.class);

        GetProductResponse actualResult = productService.updateProduct(productDto);

        assertNotNull(actualResult);
        assertEquals(productDto.getSku(),product.getSku());
    }

    @Test
    @DisplayName("Product by this id not found")
    void getProductById_shouldThrowException_whenProductNotFound() {
        Product product = createProduct();

        doReturn(Optional.empty()).when(productRepository).findById(product.getId());

        assertThrows(ApplicationException.class, () -> productService.getProductById(product.getId()));
    }

    @Test
    @DisplayName("Returns the empty product page")
    void getAllProducts_shouldThrowException_whenProductListIsEmpty(){
        doReturn(Page.empty()).when(productRepository).findAll(PageRequest.of(0,10));

        assertThrows(ApplicationException.class, () -> productService.getAllProducts(0,10));
    }

    @Test
    @DisplayName("Save product with sku that already exists")
    void saveProduct_shouldThrowException_whenProductWithThisSkuAlreadyExists(){
        CreateProductDto productDto = createProductDto();
        Product product = createProduct();

        doReturn(Optional.of(product)).when(productRepository).findBySku(productDto.getSku());

        assertThrows(ApplicationException.class, () -> productService.saveProduct(productDto));
    }

}