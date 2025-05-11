package com.spring.marketplace.docs.order;

import com.spring.marketplace.dto.GetOrderResponse;
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
        summary = "Get orders by status from database",
        description = "Returns an orders with a certain status. Also called by the circuit breaker " +
                "when the GetOrdersWithProducts endpoint throws too many errors",
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
                        description = "Get orders by status successfully",
                        content = @Content(
                                array = @ArraySchema(
                                        schema = @Schema(implementation = GetOrderResponse.class)
                                ),
                                mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Orders with this status dont exists",
                        content = @Content(
                                schema = @Schema(implementation = ErrorDetails.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "exceptionClass": "ApplicationException",
                                                    "message": "No orders found",
                                                    "timestamp": "2025-05-10T21:27:08.3312403"
                                                }
                                                """
                                )
                        )
                )

        }
)
public @interface GetOrderByStatusDoc {
}
