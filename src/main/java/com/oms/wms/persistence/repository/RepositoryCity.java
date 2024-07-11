package com.oms.wms.persistence.repository;

import com.oms.wms.persistence.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryCity extends JpaRepository<City, UUID>, RepositoryInterface<City> {
//    City findByName(String name);
//    boolean existsByNameIgnoreCaseAndIdNot(String username, UUID id);
//    boolean existsByName(String value);
//    boolean existsByNameIgnoreCase(String value);
//    Page<City> findByIdOrderByIdAsc(Pageable pageable, long id);
//    Page<City> findByNameContainingIgnoreCaseOrderByNameAsc(Pageable pageable, String name);
}