package com.spring.marketplace.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Builder
@Getter
@Schema(description = "Details about the exception")
public class ErrorDetails {

    @Schema(description = "Exception class", example = "ApplicationException")
    private String exceptionClass;
    @Schema(description = "Exception message", example = "No users found")
    private String message;
    @Schema(description = "Time of the exception")
    private LocalDateTime timestamp;
}
