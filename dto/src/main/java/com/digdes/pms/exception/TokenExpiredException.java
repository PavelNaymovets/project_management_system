package com.digdes.pms.exception;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String errorMessage) {
        super(errorMessage);
    }
}
