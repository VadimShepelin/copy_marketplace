package com.spring.marketplace.docs.order;

import com.spring.marketplace.dto.CreateOrderDto;
import com.spring.marketplace.dto.GetOrderResponse;
import com.spring.marketplace.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
        summary = "Create Order",
        description = "Try to create order and save in database",
        parameters = {
                @Parameter(
                        name = "user_id",
                        description = "Id of the user who want to create order",
                        required = true,
                        example = "550e8400-e29b-41d4-a716-446655440000"
                ),
        },
        requestBody = @RequestBody(
                description = "Request to create order",
                required = true,
                content = @Content(
                        schema = @Schema(implementation = CreateOrderDto.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Create new order successfully",
                        content = @Content(
                                schema = @Schema(implementation = GetOrderResponse.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Fail to create order",
                        content = @Content(
                                schema = @Schema(implementation = ErrorDetails.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "exceptionClass": "ApplicationException",
                                                    "message": "Insufficient quantity of goods",
                                                    "timestamp": "2025-05-10T21:27:08.3312403"
                                                }
                                                """
                                )
                        )
                )

        }
)
public @interface CreateOrderDoc {
}
