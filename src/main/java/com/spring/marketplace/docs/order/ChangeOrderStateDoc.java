package com.spring.marketplace.docs.order;

import com.spring.marketplace.dto.GetOrderResponse;
import com.spring.marketplace.dto.UpdateOrderStateDto;
import com.spring.marketplace.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.links.Link;
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
        summary = "Change order state",
        description = "Try to update state of the order",
        parameters = {
                @Parameter(
                        name = "order_id",
                        required = true,
                        description = "Id of the order",
                        example = "e1f47ac2-5b6a-42fb-9a41-8f0f9f6539d1"
                )
        },
        requestBody = @RequestBody(
                description = "Request to update order state",
                required = true,
                content = @Content(
                        schema = @Schema(implementation = UpdateOrderStateDto.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Update order successfully",
                        content = @Content(
                                array = @ArraySchema(
                                        schema = @Schema(implementation = GetOrderResponse.class)
                                ),
                                mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Order with this id dont exists",
                        content = @Content(
                                schema = @Schema(implementation = ErrorDetails.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "exceptionClass": "ApplicationException",
                                                    "message": "Failed to change order status",
                                                    "timestamp": "2025-05-10T21:41:44.7526347"
                                                }
                                                """
                                )
                        )
                )
        }
)
public @interface ChangeOrderStateDoc {
}
