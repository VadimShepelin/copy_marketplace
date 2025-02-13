package com.spring.marketplace.exception;


import com.spring.marketplace.utils.enums.ErrorType;

public class ApplicationException extends RuntimeException {
    private final ErrorType errorType;

    public ApplicationException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
