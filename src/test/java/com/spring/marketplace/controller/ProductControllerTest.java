package com.spring.marketplace.controller;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static com.spring.marketplace.util.ProductTestUtil.*;
import static io.restassured.RestAssured.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Test
    @DisplayName("Get all products have status code 200")
    public void getAllProducts_shouldHaveStatusCode200() {
       given().
               port(port).
               when().
               request("GET", getUrl())
               .then().
               statusCode(200);
    }

    @Test
    @DisplayName("Get product by id have status code 200")
    public void getProductById_shouldHaveStatusCode200() {
        given()
                .port(port)
                .when()
                .request("GET", getUrlWithId())
                .then()
                .statusCode(200)
                .body("sku",Matchers.equalTo("FOOD-APP-001"));
    }

    @Test
    @DisplayName("Get product with incorrect id have status code 400")
    public void getProductById_shouldHaveStatusCode400() {
        given().
                port(port).
                when()
                .request("GET", getUrlWithIncorrectId()).
                then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Delete product by id have status code 200")
    public void deleteProductById_shouldHaveStatusCode200() {
        given().
                port(port).
                when().
                request("DELETE", getUrlWithId())
                .then().
                statusCode(200);
    }

    @Test
    @DisplayName("Delete product with incorrect id have status code 400")
    public void deleteProductById_shouldHaveStatusCode400() {
        given().
                port(port).
                when().
                request("DELETE", getUrlWithIncorrectId())
                .then().
                statusCode(400);
    }

    @Test
    @DisplayName("Save product have status code 200")
    public void saveProduct_shouldHaveStatusCode200() {
        given().
                port(port).
                body(createProductDto()).
                contentType(ContentType.JSON).
                when().
                request("POST",getUrl()).
                then().
                statusCode(200);
    }

    @Test
    @DisplayName("Save product with null category should have status code 400")
    public void saveProduct_shouldHaveStatusCode400ThenSkuNull() {
        given().
                port(port).
                body(createProductDtoWithNullSku()).
                contentType(ContentType.JSON).
                when().
                request("POST",getUrl()).
                then().
                statusCode(400);
    }

    @Test
    @DisplayName("Update product have status code 200")
    public void updateProduct_shouldHaveStatusCode200() {
        given().
                port(port).
                body(createUpdateProductDto()).
                contentType(ContentType.JSON).
                when().
                request("PUT",getUrl()).
                then().
                statusCode(200);
    }

    @Test
    @DisplayName("Update product with empty name should have status code 400")
    public void updateProduct_shouldHaveStatusCode400ThenEmptyName() {
        given().
                port(port).
                body(createUpdateProductDtoWithEmptyName()).
                contentType(ContentType.JSON).
                when()
                .request("PUT",getUrl()).
                then().
                statusCode(400);
    }
}
