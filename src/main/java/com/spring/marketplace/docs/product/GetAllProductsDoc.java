package com.spring.marketplace.docs.product;

import com.spring.marketplace.dto.GetProductResponse;
import com.spring.marketplace.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Get all products from database",
        description = "Returns information about products from database",
        parameters = {
                @Parameter(
                        name = "pageNo",
                        description = "Page number for pagination",
                        example = "1"
                ),
                @Parameter(
                        name = "pageSize",
                        description = "Page size for pagination",
                        example = "5"
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Get all products successfully",
                        content = @Content(
                                array = @ArraySchema(
                                        schema = @Schema(implementation = GetProductResponse.class)
                                ),
                                mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "No products found in database",
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
public @interface GetAllProductsDoc {
}
