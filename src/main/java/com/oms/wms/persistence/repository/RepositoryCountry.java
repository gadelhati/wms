package com.oms.wms.persistence.repository;

import com.oms.wms.persistence.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryCountry extends JpaRepository<Country, UUID>, RepositoryInterface<Country> {

}