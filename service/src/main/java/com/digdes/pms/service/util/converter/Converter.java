package com.digdes.pms.service.util.converter;

public interface Converter<E,D> {
    E convertToEntity(D dto);
    D convertToDto(E entity);
}
