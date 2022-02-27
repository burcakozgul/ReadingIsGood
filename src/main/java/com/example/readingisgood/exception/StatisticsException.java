package com.example.readingisgood.exception;

import lombok.Data;

@Data
public class StatisticsException extends RuntimeException {

    private String message;
    private String errorCode;

    public StatisticsException(String message, String errorCode) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }
}
