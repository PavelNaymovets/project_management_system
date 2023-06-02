package com.digdes.pms.exception;

public class FieldIncorrectException extends RuntimeException {
    public FieldIncorrectException(String errorMessage) {
        super(errorMessage);
    }
}
