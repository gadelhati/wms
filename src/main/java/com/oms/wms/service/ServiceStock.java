package com.oms.wms.service;

import com.oms.wms.persistence.MapStruct;
import com.oms.wms.persistence.model.Stock;
import com.oms.wms.persistence.payload.request.DTORequestStock;
import com.oms.wms.persistence.payload.response.DTOResponseStock;
import com.oms.wms.persistence.repository.RepositoryStock;
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
public class ServiceStock {

    private final RepositoryStock repositoryStock;

    public DTOResponseStock create(DTORequestStock created){
        return MapStruct.MAPPER.toDTO(repositoryStock.save(MapStruct.MAPPER.toObject(created)));
    }
    public Page<DTOResponseStock> retrieve(Pageable pageable, String value){
        Stock object = new Stock();
        ExampleMatcher exampleMatcher = matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        try {
            Method setMethod = object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(pageable.getSort().stream().findFirst().get().getProperty()), String.class);
            setMethod.invoke(object, value);
            Example<Stock> example = Example.of(object, exampleMatcher);
            return repositoryStock.findAll(example, pageable).map(MapStruct.MAPPER::toDTO);
        } catch (IllegalArgumentException exception) {
            return repositoryStock.findById(pageable, UUID.fromString(value)).map(MapStruct.MAPPER::toDTO);
        } catch (Exception e) {
            return repositoryStock.findAll(pageable).map(MapStruct.MAPPER::toDTO);
        }
    }
    public DTOResponseStock update(UUID id, DTORequestStock updated){
        return MapStruct.MAPPER.toDTO(repositoryStock.save(MapStruct.MAPPER.toObject(updated)));
    }
    public DTOResponseStock delete(UUID id){
        Stock object = repositoryStock.findById(id).orElse(null);
        repositoryStock.deleteById(id);
        return MapStruct.MAPPER.toDTO(object);
    }
    public void delete() {
        repositoryStock.deleteAll();
    }

    public boolean existsById(UUID value) {
        return repositoryStock.findById(value).orElse(null) != null;
    }
}