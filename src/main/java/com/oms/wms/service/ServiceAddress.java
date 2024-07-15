package com.oms.wms.service;

import com.oms.wms.persistence.MapStruct;
import com.oms.wms.persistence.model.Address;
import com.oms.wms.persistence.payload.request.DTORequestAddress;
import com.oms.wms.persistence.payload.response.DTOResponseAddress;
import com.oms.wms.persistence.repository.RepositoryAddress;
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
public class ServiceAddress implements ServiceInterface<DTOResponseAddress, DTORequestAddress> {

    private final RepositoryAddress repositoryAddress;

    public DTOResponseAddress create(DTORequestAddress created){
        return MapStruct.MAPPER.toDTO(repositoryAddress.save(MapStruct.MAPPER.toObject(created)));
    }
    public Page<DTOResponseAddress> retrieve(Pageable pageable, String value){
        Address object = new Address();
        ExampleMatcher exampleMatcher = matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        try {
            Method setMethod = object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(pageable.getSort().stream().findFirst().get().getProperty()), String.class);
            setMethod.invoke(object, value);
            Example<Address> example = Example.of(object, exampleMatcher);
            return repositoryAddress.findAll(example, pageable).map(MapStruct.MAPPER::toDTO);
        } catch (IllegalArgumentException exception) {
            return repositoryAddress.findById(pageable, UUID.fromString(value)).map(MapStruct.MAPPER::toDTO);
        } catch (Exception e) {
            return repositoryAddress.findAll(pageable).map(MapStruct.MAPPER::toDTO);
        }
    }
    public DTOResponseAddress update(UUID id, DTORequestAddress updated){
        return MapStruct.MAPPER.toDTO(repositoryAddress.save(MapStruct.MAPPER.toObject(updated)));
    }
    public DTOResponseAddress delete(UUID id){
        Address object = repositoryAddress.findById(id).orElse(null);
        repositoryAddress.deleteById(id);
        return MapStruct.MAPPER.toDTO(object);
    }
    public void delete() {
        repositoryAddress.deleteAll();
    }
}