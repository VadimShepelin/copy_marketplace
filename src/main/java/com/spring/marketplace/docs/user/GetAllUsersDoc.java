package com.spring.marketplace.docs.user;

import com.spring.marketplace.dto.GetUserResponse;
import com.spring.marketplace.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
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

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Operation(
        summary = "Get all users from database",
        description = "Returns information about users from database",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Get all users successfully",
                        content = @Content(
                                array = @ArraySchema(
                                        schema = @Schema(implementation = GetUserResponse.class)
                                ),
                                mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "No users found in Database",
                        content = @Content(
                                schema = @Schema(implementation = ErrorDetails.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "exceptionClass": "ApplicationException",
                                                    "message": "No users found in database",
                                                    "timestamp": "2025-05-10T20:55:47.486748"
                                                }
                                                """
                                )

                        )
                )

        }
)
public @interface GetAllUsersDoc {
}
