package com.example.readingisgood.exception;

import lombok.Data;

@Data
public class CustomerException extends RuntimeException {

    private String message;
    private String errorCode;

    public CustomerException(String message, String errorCode) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }
}
