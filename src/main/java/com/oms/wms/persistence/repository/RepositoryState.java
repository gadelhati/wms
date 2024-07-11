package com.oms.wms.persistence.repository;

import com.oms.wms.persistence.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryState extends JpaRepository<State, UUID>, RepositoryInterface<State> {

}