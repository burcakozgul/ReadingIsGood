package com.example.readingisgood.exception;

import lombok.Data;

@Data
public class OrderException extends RuntimeException {

    private String message;
    private String errorCode;

    public OrderException(String message, String errorCode) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }
}
