package com.oms.wms.persistence.repository;

import com.oms.wms.persistence.model.Delivery;
import com.oms.wms.persistence.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryDelivery extends JpaRepository<Delivery, UUID> {

    Page<Delivery> findById(Pageable pageable, UUID uuid);
    Page<Order> findByIdOrderByIdAsc(Pageable pageable, UUID id);
}