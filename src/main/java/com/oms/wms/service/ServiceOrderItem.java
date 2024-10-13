package com.oms.wms.service;

import com.oms.wms.persistence.MapStruct;
import com.oms.wms.persistence.model.OrderItem;
import com.oms.wms.persistence.payload.request.DTORequestOrderItem;
import com.oms.wms.persistence.payload.response.DTOResponseOrderItem;
import com.oms.wms.persistence.repository.RepositoryOrderItem;
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
public class ServiceOrderItem {

    private final RepositoryOrderItem repositoryOrderItem;

    public DTOResponseOrderItem create(DTORequestOrderItem created){
        return MapStruct.MAPPER.toDTO(repositoryOrderItem.save(MapStruct.MAPPER.toObject(created)));
    }
    public Page<DTOResponseOrderItem> retrieve(Pageable pageable, String value){
        OrderItem object = new OrderItem();
        ExampleMatcher exampleMatcher = matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        try {
            Method setMethod = object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(pageable.getSort().stream().findFirst().get().getProperty()), String.class);
            setMethod.invoke(object, value);
            Example<OrderItem> example = Example.of(object, exampleMatcher);
            return repositoryOrderItem.findAll(example, pageable).map(MapStruct.MAPPER::toDTO);
        } catch (IllegalArgumentException exception) {
            return repositoryOrderItem.findById(pageable, UUID.fromString(value)).map(MapStruct.MAPPER::toDTO);
        } catch (Exception e) {
            return repositoryOrderItem.findAll(pageable).map(MapStruct.MAPPER::toDTO);
        }
    }
    public DTOResponseOrderItem update(UUID id, DTORequestOrderItem updated){
        return MapStruct.MAPPER.toDTO(repositoryOrderItem.save(MapStruct.MAPPER.toObject(updated)));
    }
    public DTOResponseOrderItem delete(UUID id){
        OrderItem object = repositoryOrderItem.findById(id).orElse(null);
        repositoryOrderItem.deleteById(id);
        return MapStruct.MAPPER.toDTO(object);
    }
    public void delete() {
        repositoryOrderItem.deleteAll();
    }

    public boolean existsById(UUID value) {
        return repositoryOrderItem.findById(value).orElse(null) != null;
    }
}