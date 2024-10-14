package com.oms.wms.service;

import com.oms.wms.persistence.MapStruct;
import com.oms.wms.persistence.model.Lot;
import com.oms.wms.persistence.payload.request.DTORequestLot;
import com.oms.wms.persistence.payload.response.DTOResponseLot;
import com.oms.wms.persistence.repository.RepositoryLot;
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
public class ServiceLot implements ServiceInterface<DTOResponseLot, DTORequestLot> {

    private final RepositoryLot repositoryLot;

    public DTOResponseLot create(DTORequestLot created){
        return MapStruct.MAPPER.toDTO(repositoryLot.save(MapStruct.MAPPER.toObject(created)));
    }
    public Page<DTOResponseLot> retrieve(Pageable pageable, String value){
        Lot object = new Lot();
        ExampleMatcher exampleMatcher = matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        try {
            Method setMethod = object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(pageable.getSort().stream().findFirst().get().getProperty()), String.class);
            setMethod.invoke(object, value);
            Example<Lot> example = Example.of(object, exampleMatcher);
            return repositoryLot.findAll(example, pageable).map(MapStruct.MAPPER::toDTO);
        } catch (NoSuchMethodException exception) {
            return repositoryLot.findById(pageable, UUID.fromString(value)).map(MapStruct.MAPPER::toDTO);
        } catch (Exception e) {
            return repositoryLot.findAll(pageable).map(MapStruct.MAPPER::toDTO);
        }
    }
    public DTOResponseLot update(UUID id, DTORequestLot updated){
        return MapStruct.MAPPER.toDTO(repositoryLot.save(MapStruct.MAPPER.toObject(updated)));
    }
    public DTOResponseLot delete(UUID id){
        Lot object = repositoryLot.findById(id).orElse(null);
        repositoryLot.deleteById(id);
        return MapStruct.MAPPER.toDTO(object);
    }
    public void delete() {
        repositoryLot.deleteAll();
    }
}