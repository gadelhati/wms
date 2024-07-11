package com.oms.wms.service;

import com.oms.wms.persistence.MapStruct;
import com.oms.wms.persistence.model.Order;
import com.oms.wms.persistence.payload.request.DTORequestOrder;
import com.oms.wms.persistence.payload.response.DTOResponseOrder;
import com.oms.wms.persistence.repository.RepositoryOrder;
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
public class ServiceOrder {

    private final RepositoryOrder repositoryOrder;

    public DTOResponseOrder create(DTORequestOrder created){
        return MapStruct.MAPPER.toDTO(repositoryOrder.save(MapStruct.MAPPER.toObject(created)));
    }
    public Page<DTOResponseOrder> retrieve(Pageable pageable, String value){
        Order object = new Order();
        ExampleMatcher exampleMatcher = matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        try {
            Method setMethod = object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(pageable.getSort().stream().findFirst().get().getProperty()), String.class);
            setMethod.invoke(object, value);
            Example<Order> example = Example.of(object, exampleMatcher);
            return repositoryOrder.findAll(example, pageable).map(MapStruct.MAPPER::toDTO);
        } catch (IllegalArgumentException exception) {
            return repositoryOrder.findById(pageable, UUID.fromString(value)).map(MapStruct.MAPPER::toDTO);
        } catch (Exception e) {
            return repositoryOrder.findAll(pageable).map(MapStruct.MAPPER::toDTO);
        }
    }
    public DTOResponseOrder update(UUID id, DTORequestOrder updated){
        return MapStruct.MAPPER.toDTO(repositoryOrder.save(MapStruct.MAPPER.toObject(updated)));
    }
    public DTOResponseOrder delete(UUID id){
        Order object = repositoryOrder.findById(id).orElse(null);
        repositoryOrder.deleteById(id);
        return MapStruct.MAPPER.toDTO(object);
    }
    public void delete() {
        repositoryOrder.deleteAll();
    }
}