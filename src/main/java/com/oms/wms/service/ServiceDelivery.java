package com.oms.wms.service;

import com.oms.wms.persistence.MapStruct;
import com.oms.wms.persistence.model.Delivery;
import com.oms.wms.persistence.payload.request.DTORequestDelivery;
import com.oms.wms.persistence.payload.response.DTOResponseDelivery;
import com.oms.wms.persistence.repository.RepositoryDelivery;
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
public class ServiceDelivery {

    private final RepositoryDelivery repositoryDelivery;

    public DTOResponseDelivery create(DTORequestDelivery created){
        return MapStruct.MAPPER.toDTO(repositoryDelivery.save(MapStruct.MAPPER.toObject(created)));
    }
    public Page<DTOResponseDelivery> retrieve(Pageable pageable, String value){
        Delivery object = new Delivery();
        ExampleMatcher exampleMatcher = matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        try {
            Method setMethod = object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(pageable.getSort().stream().findFirst().get().getProperty()), String.class);
            setMethod.invoke(object, value);
            Example<Delivery> example = Example.of(object, exampleMatcher);
            return repositoryDelivery.findAll(example, pageable).map(MapStruct.MAPPER::toDTO);
        } catch (NoSuchMethodException exception) {
            return repositoryDelivery.findById(pageable, UUID.fromString(value)).map(MapStruct.MAPPER::toDTO);
        } catch (Exception e) {
            return repositoryDelivery.findAll(pageable).map(MapStruct.MAPPER::toDTO);
        }
    }
    public DTOResponseDelivery update(UUID id, DTORequestDelivery updated){
        return MapStruct.MAPPER.toDTO(repositoryDelivery.save(MapStruct.MAPPER.toObject(updated)));
    }
    public DTOResponseDelivery delete(UUID id){
        Delivery object = repositoryDelivery.findById(id).orElse(null);
        repositoryDelivery.deleteById(id);
        return MapStruct.MAPPER.toDTO(object);
    }
    public void delete() {
        repositoryDelivery.deleteAll();
    }
}