package com.digdes.pms.service.util.service;

import java.util.List;

public interface ServiceCRUD<D,F> {
    D create(D d);
    D update(D d);
    D findById(Long id);
    D deleteById(Long id);
    List<D> findAllByFilter(F f);
}
