package com.oms.wms.service;

import com.oms.wms.persistence.MapStruct;
import com.oms.wms.persistence.model.City;
import com.oms.wms.persistence.payload.request.DTORequestCity;
import com.oms.wms.persistence.payload.response.DTOResponseCity;
import com.oms.wms.persistence.repository.RepositoryCity;
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
public class ServiceCity {

    private final RepositoryCity repositoryCity;

    public DTOResponseCity create(DTORequestCity created){
        return MapStruct.MAPPER.toDTO(repositoryCity.save(MapStruct.MAPPER.toObject(created)));
    }
    public Page<DTOResponseCity> retrieve(Pageable pageable, String value){
        City object = new City();
        ExampleMatcher exampleMatcher = matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        try {
            Method setMethod = object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(pageable.getSort().stream().findFirst().get().getProperty()), String.class);
            setMethod.invoke(object, value);
            Example<City> example = Example.of(object, exampleMatcher);
            return repositoryCity.findAll(example, pageable).map(MapStruct.MAPPER::toDTO);
        } catch (NoSuchMethodException exception) {
            return repositoryCity.findById(pageable, UUID.fromString(value)).map(MapStruct.MAPPER::toDTO);
        } catch (Exception e) {
            return repositoryCity.findAll(pageable).map(MapStruct.MAPPER::toDTO);
        }
    }
    public DTOResponseCity update(UUID id, DTORequestCity updated){
        return MapStruct.MAPPER.toDTO(repositoryCity.save(MapStruct.MAPPER.toObject(updated)));
    }
    public DTOResponseCity delete(UUID id){
        City object = repositoryCity.findById(id).orElse(null);
        repositoryCity.deleteById(id);
        return MapStruct.MAPPER.toDTO(object);
    }
    public void delete() {
        repositoryCity.deleteAll();
    }
    public boolean existsByName(String value) {
        return repositoryCity.existsByNameIgnoreCase(value);
    }
    public boolean existsByNameAndIdNot(String value, UUID id) {
        return repositoryCity.existsByNameIgnoreCaseAndIdNot(value, id);
    }
    public boolean existsByCode(String value) {
        return repositoryCity.existsByNameIgnoreCase(value);
    }
    public boolean existsByCodeAndIdNot(String value, UUID id) {
        return repositoryCity.existsByNameIgnoreCaseAndIdNot(value, id);
    }
}