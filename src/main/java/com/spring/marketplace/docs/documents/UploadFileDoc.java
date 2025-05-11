package com.spring.marketplace.docs.documents;

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
import org.springframework.web.multipart.MultipartFile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Upload file",
        description = "Try to upload and save file from the user",
        requestBody = @RequestBody(
                description = "Request to upload file from the user",
                required = true,
                content = @Content(
                        schema = @Schema(implementation = MultipartFile.class),
                        mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Upload new file successfully",
                        content = @Content(
                                schema = @Schema(implementation = String.class),
                                mediaType = MediaType.TEXT_PLAIN_VALUE,
                                examples = @ExampleObject(
                                        "File was upload successfully"
                                )
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Fail to upload file",
                        content = @Content(
                                schema = @Schema(implementation = ErrorDetails.class),
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                examples = @ExampleObject(
                                        value = """
                                                {
                                                    "exceptionClass": "ApplicationException",
                                                    "message": "Failed to upload file",
                                                    "timestamp": "2025-05-11T13:30:27.1909459"
                                                }
                                                """
                                )
                        )
                )

        }
)
public @interface UploadFileDoc {
}
