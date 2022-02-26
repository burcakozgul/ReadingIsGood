package com.example.readingisgood.exception;

import lombok.Data;

@Data
public class AuthException extends RuntimeException {

    private String message;
    private String errorCode;

    public AuthException(String message, String errorCode) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }
}
