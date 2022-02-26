package com.example.readingisgood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomerException.class})
    public ResponseEntity<Object> handleApiException(final CustomerException ex) {
        final ApiError apiError;

        apiError = createApiError(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BookException.class})
    public ResponseEntity<Object> handleApiException(final BookException ex) {
        final ApiError apiError;

        apiError = createApiError(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({OrderException.class})
    public ResponseEntity<Object> handleApiException(final OrderException ex) {
        final ApiError apiError;

        apiError = createApiError(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AuthException.class})
    public ResponseEntity<Object> handleApiException(final AuthException ex) {
        final ApiError apiError;

        apiError = createApiError(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    private ApiError createApiError(String errorCode, String message) {
        return new ApiError(message, errorCode);
    }
}
