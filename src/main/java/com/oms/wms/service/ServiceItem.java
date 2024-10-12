package com.oms.wms.service;

import com.oms.wms.persistence.MapStruct;
import com.oms.wms.persistence.model.Item;
import com.oms.wms.persistence.payload.request.DTORequestItem;
import com.oms.wms.persistence.payload.response.DTOResponseItem;
import com.oms.wms.persistence.repository.RepositoryItem;
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
public class ServiceItem {

    private final RepositoryItem repositoryItem;

    public DTOResponseItem create(DTORequestItem created){
        return MapStruct.MAPPER.toDTO(repositoryItem.save(MapStruct.MAPPER.toObject(created)));
    }
    public Page<DTOResponseItem> retrieve(Pageable pageable, String value){
        Item object = new Item();
        ExampleMatcher exampleMatcher = matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        try {
            Method setMethod = object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(pageable.getSort().stream().findFirst().get().getProperty()), String.class);
            setMethod.invoke(object, value);
            Example<Item> example = Example.of(object, exampleMatcher);
            return repositoryItem.findAll(example, pageable).map(MapStruct.MAPPER::toDTO);
        } catch (IllegalArgumentException exception) {
            return repositoryItem.findById(pageable, UUID.fromString(value)).map(MapStruct.MAPPER::toDTO);
        } catch (Exception e) {
            return repositoryItem.findAll(pageable).map(MapStruct.MAPPER::toDTO);
        }
    }
    public DTOResponseItem update(UUID id, DTORequestItem updated){
        return MapStruct.MAPPER.toDTO(repositoryItem.save(MapStruct.MAPPER.toObject(updated)));
    }
    public DTOResponseItem delete(UUID id){
        Item object = repositoryItem.findById(id).orElse(null);
        repositoryItem.deleteById(id);
        return MapStruct.MAPPER.toDTO(object);
    }
    public void delete() {
        repositoryItem.deleteAll();
    }

    public boolean existsByGtin(String value) {
        return repositoryItem.existsByGtinIgnoreCase(value);
    }
    public boolean existsByGtinAndIdNot(String value, UUID id) {
        return repositoryItem.existsByGtinIgnoreCaseAndIdNot(value, id);
    }
}