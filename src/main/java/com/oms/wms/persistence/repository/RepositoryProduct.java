package com.oms.wms.persistence.repository;

import com.oms.wms.persistence.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryProduct extends JpaRepository<Product, UUID> {

    Page<Product> findById(Pageable pageable, UUID uuid);
    Page<Product> findByIdOrderByIdAsc(Pageable pageable, UUID id);
}