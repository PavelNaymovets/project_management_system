package com.digdes.pms.exception;

public class ProjectStatusIncorrectException extends RuntimeException {
    public ProjectStatusIncorrectException(String errorMessage) {
        super(errorMessage);
    }
}
