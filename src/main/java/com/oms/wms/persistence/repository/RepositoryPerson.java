package com.oms.wms.persistence.repository;

import com.oms.wms.persistence.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryPerson extends JpaRepository<Person, UUID>, RepositoryInterface<Person> {

}