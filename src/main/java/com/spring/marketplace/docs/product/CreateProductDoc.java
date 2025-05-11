package com.spring.marketplace.docs.product;

import com.spring.marketplace.dto.CreateProductDto;
import com.spring.marketplace.dto.GetProductResponse;
import com.spring.marketplace.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
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
        summary = "Create Product",
        description = "Try to create product and save in database",
        requestBody = @RequestBody(
                description = "Request to create product",
                required = true,
                content = @Content(
                        schema = @Schema(implementation = CreateProductDto.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Create new product successfully",
                        content = @Content(
                                schema = @Schema(implementation = GetProductResponse.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Fail to create product",
                        content = @Content(
                                schema = @Schema(implementation = ErrorDetails.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "exceptionClass": "MethodArgumentNotValidException",
                                                    "message": "org.springframework.web.bind.MethodArgumentNotValidException",
                                                    "timestamp": "2025-05-10T21:02:56.8961754"
                                                }
                                                """
                                )
                        )
                )

        }
)
public @interface CreateProductDoc {
}
