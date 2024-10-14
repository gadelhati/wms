package com.oms.wms.service;

import com.oms.wms.persistence.MapStruct;
import com.oms.wms.persistence.model.State;
import com.oms.wms.persistence.payload.request.DTORequestState;
import com.oms.wms.persistence.payload.response.DTOResponseState;
import com.oms.wms.persistence.repository.RepositoryState;
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
public class ServiceState {

    private final RepositoryState repositoryState;

    public DTOResponseState create(DTORequestState created){
        return MapStruct.MAPPER.toDTO(repositoryState.save(MapStruct.MAPPER.toObject(created)));
    }
    public Page<DTOResponseState> retrieve(Pageable pageable, String value){
        State object = new State();
        ExampleMatcher exampleMatcher = matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        try {
            Method setMethod = object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(pageable.getSort().stream().findFirst().get().getProperty()), String.class);
            setMethod.invoke(object, value);
            Example<State> example = Example.of(object, exampleMatcher);
            return repositoryState.findAll(example, pageable).map(MapStruct.MAPPER::toDTO);
        } catch (NoSuchMethodException exception) {
            return repositoryState.findById(pageable, UUID.fromString(value)).map(MapStruct.MAPPER::toDTO);
        } catch (Exception e) {
            return repositoryState.findAll(pageable).map(MapStruct.MAPPER::toDTO);
        }
    }
    public DTOResponseState update(UUID id, DTORequestState updated){
        return MapStruct.MAPPER.toDTO(repositoryState.save(MapStruct.MAPPER.toObject(updated)));
    }
    public DTOResponseState delete(UUID id){
        State object = repositoryState.findById(id).orElse(null);
        repositoryState.deleteById(id);
        return MapStruct.MAPPER.toDTO(object);
    }
    public void delete() {
        repositoryState.deleteAll();
    }
    public boolean existsByName(String value) {
        return repositoryState.existsByNameIgnoreCase(value);
    }
    public boolean existsByNameAndIdNot(String value, UUID id) {
        return repositoryState.existsByNameIgnoreCaseAndIdNot(value, id);
    }
    public boolean existsByCode(String value) {
        return repositoryState.existsByNameIgnoreCase(value);
    }
    public boolean existsByCodeAndIdNot(String value, UUID id) {
        return repositoryState.existsByNameIgnoreCaseAndIdNot(value, id);
    }
}