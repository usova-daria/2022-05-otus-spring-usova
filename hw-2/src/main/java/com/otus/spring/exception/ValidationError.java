package com.otus.spring.exception;

public class ValidationError extends RuntimeException {

    public ValidationError() {
        super();
    }

    public ValidationError(String message) {
        super(message);
    }

    public ValidationError(String message, Throwable cause) {
        super(message, cause);
    }

}
