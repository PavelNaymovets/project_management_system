package com.digdes.pms.exception;

public class EmailSendException extends RuntimeException {
    public EmailSendException(String errorMessage) {
        super(errorMessage);
    }
}
