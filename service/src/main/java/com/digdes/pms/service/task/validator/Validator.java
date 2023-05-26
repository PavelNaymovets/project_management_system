package com.digdes.pms.service.task.validator;

public interface Validator<E> {
    void validate(E e);
}
