package com.oms.wms.service;

import com.oms.wms.persistence.MapStruct;
import com.oms.wms.persistence.model.Product;
import com.oms.wms.persistence.payload.request.DTORequestProduct;
import com.oms.wms.persistence.payload.response.DTOResponseProduct;
import com.oms.wms.persistence.repository.RepositoryProduct;
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
public class ServiceProduct {

    private final RepositoryProduct repositoryProduct;

    public DTOResponseProduct create(DTORequestProduct created){
        return MapStruct.MAPPER.toDTO(repositoryProduct.save(MapStruct.MAPPER.toObject(created)));
    }
    public Page<DTOResponseProduct> retrieve(Pageable pageable, String value){
        Product object = new Product();
        ExampleMatcher exampleMatcher = matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        try {
            Method setMethod = object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(pageable.getSort().stream().findFirst().get().getProperty()), String.class);
            setMethod.invoke(object, value);
            Example<Product> example = Example.of(object, exampleMatcher);
            return repositoryProduct.findAll(example, pageable).map(MapStruct.MAPPER::toDTO);
        } catch (IllegalArgumentException exception) {
            return repositoryProduct.findById(pageable, UUID.fromString(value)).map(MapStruct.MAPPER::toDTO);
        } catch (Exception e) {
            return repositoryProduct.findAll(pageable).map(MapStruct.MAPPER::toDTO);
        }
    }
    public DTOResponseProduct update(UUID id, DTORequestProduct updated){
        return MapStruct.MAPPER.toDTO(repositoryProduct.save(MapStruct.MAPPER.toObject(updated)));
    }
    public DTOResponseProduct delete(UUID id){
        Product object = repositoryProduct.findById(id).orElse(null);
        repositoryProduct.deleteById(id);
        return MapStruct.MAPPER.toDTO(object);
    }
    public void delete() {
        repositoryProduct.deleteAll();
    }
}