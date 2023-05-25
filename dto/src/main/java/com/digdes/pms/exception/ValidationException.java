package com.digdes.pms.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ValidationException extends RuntimeException {
    private List<String> errorMessage;

    public ValidationException(List<String> errorMessage) {
        super(errorMessage.stream().collect(Collectors.joining(",")));
        this.errorMessage = errorMessage;
    }
}
