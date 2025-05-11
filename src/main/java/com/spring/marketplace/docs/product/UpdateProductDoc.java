package com.spring.marketplace.docs.product;

import com.spring.marketplace.dto.GetProductResponse;
import com.spring.marketplace.dto.UpdateProductDto;
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
        summary = "Update Product",
        description = "Try to update product in database",
        requestBody = @RequestBody(
                description = "Request to update product",
                required = true,
                content = @Content(
                        schema = @Schema(implementation = UpdateProductDto.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Update product successfully",
                        content = @Content(
                                schema = @Schema(implementation = GetProductResponse.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Fail to update product",
                        content = @Content(
                                schema = @Schema(implementation = ErrorDetails.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "exceptionClass": "MethodArgumentNotValidException",
                                                    "message": "Validation failed for argument [0] in public com.spring.marketplace.dto.GetProductResponse",
                                                    "timestamp": "2025-05-10T21:24:26.7099535"
                                                }
                                                """
                                )
                        )
                )

        }
)
public @interface UpdateProductDoc {
}
