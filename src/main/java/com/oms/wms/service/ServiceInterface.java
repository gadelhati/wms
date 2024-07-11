package com.oms.wms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ServiceInterface<T, S> {
    T create(S created);
    Page<T> retrieve(Pageable pageable, String value);
    T update(UUID id, S updated);
    T delete(UUID id);
    void delete();
}