package com.oms.wms.service;

import com.oms.wms.persistence.MapStruct;
import com.oms.wms.persistence.model.Country;
import com.oms.wms.persistence.payload.request.DTORequestCountry;
import com.oms.wms.persistence.payload.response.DTOResponseCountry;
import com.oms.wms.persistence.repository.RepositoryCountry;
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
public class ServiceCountry implements ServiceInterface<DTOResponseCountry, DTORequestCountry> {

    private final RepositoryCountry repositoryCountry;

    public DTOResponseCountry create(DTORequestCountry created){
        return MapStruct.MAPPER.toDTO(repositoryCountry.save(MapStruct.MAPPER.toObject(created)));
    }
    public Page<DTOResponseCountry> retrieve(Pageable pageable, String value){
        Country object = new Country();
        ExampleMatcher exampleMatcher = matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        try {
            Method setMethod = object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(pageable.getSort().stream().findFirst().get().getProperty()), String.class);
            setMethod.invoke(object, value);
            Example<Country> example = Example.of(object, exampleMatcher);
            return repositoryCountry.findAll(example, pageable).map(MapStruct.MAPPER::toDTO);
        } catch (NoSuchMethodException exception) {
            return repositoryCountry.findById(pageable, UUID.fromString(value)).map(MapStruct.MAPPER::toDTO);
        } catch (Exception e) {
            return repositoryCountry.findAll(pageable).map(MapStruct.MAPPER::toDTO);
        }
    }
    public DTOResponseCountry update(UUID id, DTORequestCountry updated){
        return MapStruct.MAPPER.toDTO(repositoryCountry.save(MapStruct.MAPPER.toObject(updated)));
    }
    public DTOResponseCountry delete(UUID id){
        Country object = repositoryCountry.findById(id).orElse(null);
        repositoryCountry.deleteById(id);
        return MapStruct.MAPPER.toDTO(object);
    }
    public void delete() {
        repositoryCountry.deleteAll();
    }
    public boolean existsByName(String value) {
        return repositoryCountry.existsByNameIgnoreCase(value);
    }
    public boolean existsByNameAndIdNot(String value, UUID id) {
        return repositoryCountry.existsByNameIgnoreCaseAndIdNot(value, id);
    }
}