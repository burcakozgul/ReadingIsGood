package com.example.readingisgood.exception;

import lombok.Data;

@Data
public class UserException extends RuntimeException {

    private String message;
    private String errorCode;

    public UserException(String message, String errorCode) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }
}
