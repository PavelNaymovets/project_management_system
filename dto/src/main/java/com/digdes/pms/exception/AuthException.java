package com.digdes.pms.exception;

public class AuthException extends RuntimeException {
    public AuthException(String errorMessage) {
        super(errorMessage);
    }
}
