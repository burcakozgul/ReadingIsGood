package com.example.readingisgood.exception;

import lombok.Data;

@Data
public class ApiError {

    private String message;
    private String errorCode;

    public ApiError(String message, String errorCode) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }
}
