package org.example.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public enum ErrorCode {
    USER_EXIST(1001, "User already exists"),
    USER_NOT_FOUND(1002, "User not found"),
    CATEGORY_NOT_FOUND(1003, "Category not found"),
    PRODUCT_NOT_FOUND(1004, "Product not found"),
    ORDER_NOT_FOUND(1005, "Order not found"),
    ORDER_DETAIL_NOT_FOUND(1006, "Order detail not found")
    ;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    private  int code;
    private String message;
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
