package com.spring.marketplace.utils.enums;

public enum ErrorType {
    PRODUCT_NOT_FOUND("No such product"),
    NO_PRODUCTS_FOUND("No products found"),
    PRODUCT_DONT_EXISTS("Product dont exists"),
    FAILED_TO_GET_LIST_OF_FILES("Failed to get list of files"),
    UNIQUE_CONSTRAINT_EXCEPTION("A product with the same sku already exists"),
    FAILED_TO_UPLOAD_FILE("Failed to upload file"),
    FAILED_TO_DOWNLOAD_FILE("Failed to download file"),
    FAILED_TO_CONVERT_CURRENCY("Failed to convert currency"),;


    private final String message;

    ErrorType(String message) {
        this.message = message;
    };


    public String getMessage() {
        return message;
    }
}