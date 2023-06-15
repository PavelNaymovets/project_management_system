package com.digdes.pms.exception;

public class NotProjectMemberException extends RuntimeException {
    public NotProjectMemberException(String errorMessage) {
        super(errorMessage);
    }
}
