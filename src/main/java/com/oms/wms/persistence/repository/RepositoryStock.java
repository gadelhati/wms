package com.oms.wms.persistence.repository;

import com.oms.wms.persistence.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryStock extends JpaRepository<Stock, UUID> {

    Page<Stock> findById(Pageable pageable, UUID uuid);
    Page<Stock> findByIdOrderByIdAsc(Pageable pageable, UUID id);
}