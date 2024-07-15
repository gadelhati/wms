package com.oms.wms.persistence.repository;

import com.oms.wms.persistence.model.Lot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryLot extends JpaRepository<Lot, UUID> {

    Page<Lot> findById(Pageable pageable, UUID uuid);
    Page<Lot> findByIdOrderByIdAsc(Pageable pageable, UUID id);
}