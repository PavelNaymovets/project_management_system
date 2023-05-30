package com.digdes.pms.exception;

public class TaskStatusIncorrectException extends RuntimeException {
    public TaskStatusIncorrectException(String errorMessage) {
        super(errorMessage);
    }
}
