package com.digdes.pms.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException {
    private String errorMessage;

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
