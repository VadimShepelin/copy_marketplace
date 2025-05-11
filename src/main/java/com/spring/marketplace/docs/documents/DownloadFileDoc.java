package com.spring.marketplace.docs.documents;

import com.spring.marketplace.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Download file",
        description = "Try to download file from the server and give it to user",
        parameters = {
                @Parameter(
                        name = "fileName",
                        description = "Name of the file what we want to download",
                        required = true,
                        example = "1c.jpg"
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Download file successfully",
                        content = @Content(
                                schema = @Schema(implementation = Resource.class),
                                mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "No file with this name",
                        content = @Content(
                                schema = @Schema(implementation = ErrorDetails.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "exceptionClass": "ApplicationException",
                                                    "message": "Failed to download file",
                                                    "timestamp": "2025-05-11T13:33:25.3938829"
                                                }
                                                """
                                )
                        )
                )

        }
)
public @interface DownloadFileDoc {
}
