package com.oms.wms.service;

import com.oms.wms.persistence.MapStruct;
import com.oms.wms.persistence.model.Person;
import com.oms.wms.persistence.payload.request.DTORequestPerson;
import com.oms.wms.persistence.payload.response.DTOResponsePerson;
import com.oms.wms.persistence.repository.RepositoryPerson;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.UUID;

import static org.springframework.data.domain.ExampleMatcher.matching;

@Service @RequiredArgsConstructor
public class ServicePerson implements ServiceInterface<DTOResponsePerson, DTORequestPerson> {

    private final RepositoryPerson repositoryPerson;

    public DTOResponsePerson create(DTORequestPerson created){
        return MapStruct.MAPPER.toDTO(repositoryPerson.save(MapStruct.MAPPER.toObject(created)));
    }
    public Page<DTOResponsePerson> retrieve(Pageable pageable, String value){
        Person object = new Person();
        ExampleMatcher exampleMatcher = matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        try {
            Method setMethod = object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(pageable.getSort().stream().findFirst().get().getProperty()), String.class);
            setMethod.invoke(object, value);
            Example<Person> example = Example.of(object, exampleMatcher);
            return repositoryPerson.findAll(example, pageable).map(MapStruct.MAPPER::toDTO);
        } catch (IllegalArgumentException exception) {
            return repositoryPerson.findById(pageable, UUID.fromString(value)).map(MapStruct.MAPPER::toDTO);
        } catch (Exception e) {
            return repositoryPerson.findAll(pageable).map(MapStruct.MAPPER::toDTO);
        }
    }
    public DTOResponsePerson update(UUID id, DTORequestPerson updated){
        return MapStruct.MAPPER.toDTO(repositoryPerson.save(MapStruct.MAPPER.toObject(updated)));
    }
    public DTOResponsePerson delete(UUID id){
        Person object = repositoryPerson.findById(id).orElse(null);
        repositoryPerson.deleteById(id);
        return MapStruct.MAPPER.toDTO(object);
    }
    public void delete() {
        repositoryPerson.deleteAll();
    }
    public boolean existsByName(String value) {
        return repositoryPerson.existsByNameIgnoreCase(value);
    }
    public boolean existsByNameAndIdNot(String value, UUID id) {
        return repositoryPerson.existsByNameIgnoreCaseAndIdNot(value, id);
    }
}