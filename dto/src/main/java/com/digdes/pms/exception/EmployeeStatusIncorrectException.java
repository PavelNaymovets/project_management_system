package com.digdes.pms.exception;

public class EmployeeStatusIncorrectException extends RuntimeException {
    public EmployeeStatusIncorrectException(String errorMessage) {
        super(errorMessage);
    }
}
