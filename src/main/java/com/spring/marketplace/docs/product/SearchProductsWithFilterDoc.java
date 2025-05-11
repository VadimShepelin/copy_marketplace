package com.spring.marketplace.docs.product;

import com.spring.marketplace.dto.GetProductResponse;
import com.spring.marketplace.dto.ProductFilterDto;
import com.spring.marketplace.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Get products with filter",
        description = "Try to get products with filter from database",
        requestBody = @RequestBody(
                description = "Request to get products with filter",
                required = true,
                content = @Content(
                        schema = @Schema(implementation = ProductFilterDto.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Get products with filter successfully",
                        content = @Content(
                                array = @ArraySchema(
                                    schema =  @Schema(implementation = GetProductResponse.class)
                                ),
                                mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Fail to get products",
                        content = @Content(
                                schema = @Schema(implementation = ErrorDetails.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "exceptionClass": "ApplicationException",
                                                    "message": "No products found",
                                                    "timestamp": "2025-05-10T21:17:47.7373239"
                                                }
                                                """
                                )
                        )
                )
        }
)
public @interface SearchProductsWithFilterDoc {
}
