package com.digdes.pms.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeHasDeletedStatusException extends RuntimeException {
    private String errorMessage;

    public EmployeeHasDeletedStatusException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
