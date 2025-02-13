package com.spring.marketplace.exception;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorDetails {
    private String exceptionClass;
    private String message;
    private LocalDateTime timestamp;
}
