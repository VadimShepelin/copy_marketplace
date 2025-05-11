package com.spring.marketplace.docs.order;

import com.spring.marketplace.events.EventSource;
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
        summary = "Handle order event",
        description = "We are creating new event by postman(or other technology) and " +
                "send CreateOrderEvent if we want to create new order, CancelledOrderEvent " +
                "if we want to reject some order or CompletedOrderEvent if we want to complete " +
                "order. We can use all this events because we have base event - EventSource",
        requestBody = @RequestBody(
                description = "Request to handle new order event",
                required = true,
                content = @Content(
                        schema = @Schema(
                                implementation = EventSource.class,
                                description = "Base interface for all events"
                        ),
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = @ExampleObject(
                                value = """
                                        {
                                        	"event": "cancelled",
                                            "orderId": "e1f47ac2-5b6a-42fb-9a41-8f0f9f6539d1"
                                        }
                                        """
                        )
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Handle event successfully",
                        content = @Content(
                                schema = @Schema(implementation = String.class),
                                mediaType = MediaType.TEXT_PLAIN_VALUE,
                                examples = @ExampleObject(
                                        value = "Order event handled successfully"
                                )
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Fail to handle event",
                        content = @Content(
                                schema = @Schema(implementation = ErrorDetails.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "exceptionClass": "ApplicationException",
                                                    "message": "Not such order",
                                                    "timestamp": "2025-05-10T21:45:32.9134572"
                                                }
                                                """
                                )
                        )
                )

        }
)
public @interface HandleOrderEventDoc {
}
