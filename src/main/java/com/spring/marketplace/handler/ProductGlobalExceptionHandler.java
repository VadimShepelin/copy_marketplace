package com.spring.marketplace.handler;

import com.spring.marketplace.exception.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;


@ControllerAdvice
public class ProductGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex) {
        return ResponseEntity.badRequest()
                .body(ErrorDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .exceptionClass(ex.getClass().getSimpleName())
                        .message(ex.getMessage())
                        .build());
    }

}
