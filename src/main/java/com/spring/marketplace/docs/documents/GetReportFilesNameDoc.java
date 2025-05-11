package com.spring.marketplace.docs.documents;

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
        summary = "Get report files name",
        description = "Returns file names that are the result of filtered queries",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully get a list of files names",
                        content = @Content(
                                array = @ArraySchema(
                                        schema = @Schema(implementation = String.class)
                                ),
                                mediaType = MediaType.TEXT_PLAIN_VALUE,
                                examples = @ExampleObject(
                                        value = """
                                                   report_20250510_184836.xlsx        
                                                   report_20250510_184837.xlsx
                                                """
                                )
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Fail to get list of files names",
                        content = @Content(
                                schema = @Schema(implementation = ErrorDetails.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "exceptionClass": "ApplicationException",
                                                    "message": "Failed to get list of files",
                                                    "timestamp": "2025-05-11T13:32:05.0150062"
                                                }
                                                """
                                )
                        )
                )

        }
)
public @interface GetReportFilesNameDoc {
}
