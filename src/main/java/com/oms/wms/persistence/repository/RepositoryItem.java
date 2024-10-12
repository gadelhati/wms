package com.oms.wms.persistence.repository;

import com.oms.wms.persistence.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryItem extends JpaRepository<Item, UUID> {

    Page<Item> findById(Pageable pageable, UUID uuid);
    Page<Item> findByIdOrderByIdAsc(Pageable pageable, UUID id);
    boolean existsByGtinIgnoreCase(String email);
    boolean existsByGtinIgnoreCaseAndIdNot(String email, UUID id);
}