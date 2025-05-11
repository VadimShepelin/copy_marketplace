package com.spring.marketplace.utils.enums;

public enum ErrorType {
    PRODUCT_NOT_FOUND("No such product"),
    NO_PRODUCTS_FOUND("No products found"),
    PRODUCT_DONT_EXISTS("Product dont exists"),
    FAILED_TO_GET_LIST_OF_FILES("Failed to get list of files"),
    UNIQUE_CONSTRAINT_EXCEPTION("A product with the same sku already exists"),
    FAILED_TO_UPLOAD_FILE("Failed to upload file"),
    FAILED_TO_DOWNLOAD_FILE("Failed to download file"),
    FAILED_TO_CONVERT_CURRENCY("Failed to convert currency"),
    NO_SUCH_USER("No such user"),
    NOT_SUCH_ORDER("Not such order"),
    INSUFFICIENT_QUANTITY_OF_PRODUCTS("Insufficient quantity of goods"),
    FAILED_TO_CHANGE_ORDER_STATUS("Failed to change order status"),
    FAILED_TO_UPDATE_ORDER_PRODUCTS("Failed to update order products"),
    NO_ORDERS_FOUND("No orders found in database"),
    NO_USERS_FOUND("No users found in database");


    private final String message;

    ErrorType(String message) {
        this.message = message;
    };


    public String getMessage() {
        return message;
    }
}