package com.example.readingisgood.exception;

import lombok.Data;

@Data
public class BookException extends RuntimeException {

    private String message;
    private String errorCode;

    public BookException(String message, String errorCode) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }
}
