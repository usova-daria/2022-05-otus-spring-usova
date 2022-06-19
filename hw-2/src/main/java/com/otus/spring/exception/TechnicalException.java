package com.otus.spring.exception;

public class TechnicalException extends RuntimeException {

    public TechnicalException() {
    }

    public TechnicalException(Throwable cause) {
        super(cause);
    }

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

}
