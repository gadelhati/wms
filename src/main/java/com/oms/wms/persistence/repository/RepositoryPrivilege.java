package com.oms.wms.persistence.repository;

import com.oms.wms.persistence.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryPrivilege extends JpaRepository<Privilege, UUID>, RepositoryInterface<Privilege> {

}