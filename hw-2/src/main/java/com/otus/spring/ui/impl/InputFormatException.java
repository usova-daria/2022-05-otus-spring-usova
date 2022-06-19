package com.otus.spring.ui.impl;

public class InputFormatException extends RuntimeException {

    private final String correctFormat;

    public InputFormatException(String correctFormat) {
        this.correctFormat = correctFormat;
    }

    public String getCorrectFormat() {
        return correctFormat;
    }

}
