package com.oms.wms.persistence.repository;

import com.oms.wms.persistence.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryAddress extends JpaRepository<Address, UUID> {

    Page<Address> findById(Pageable pageable, UUID uuid);
    Page<Address> findByIdOrderByIdAsc(Pageable pageable, UUID id);
}