package com.spring.marketplace.docs.product;

import com.spring.marketplace.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
        summary = "Delete product",
        description = "Delete product by id from database ",
        parameters = {
                @Parameter(
                        name = "id",
                        required = true,
                        description = "Product id",
                        example = "e1f47ac2-5b6a-42fb-9a41-8f0f9f6539d1"
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Delete product successfully",
                        content = @Content(
                                schema = @Schema(implementation = String.class),
                                mediaType = MediaType.TEXT_PLAIN_VALUE,
                                examples = @ExampleObject(
                                        value = "Product was deleted"
                                )
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Product with this id dont exists",
                        content = @Content(
                                schema = @Schema(implementation = ErrorDetails.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "exceptionClass": "ApplicationException",
                                                    "message": "No such product",
                                                    "timestamp": "2025-05-10T21:17:47.7373239"
                                                }
                                                """
                                )
                        )
                )
        }
)
public @interface DeleteProductByIdDoc {
}
