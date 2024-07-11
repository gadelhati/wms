package com.oms.wms.persistence.repository;

import com.oms.wms.persistence.model.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryOrderItem extends JpaRepository<OrderItem, UUID> {

    Page<OrderItem> findById(Pageable pageable, UUID uuid);
    Page<OrderItem> findByIdOrderByIdAsc(Pageable pageable, UUID id);
}