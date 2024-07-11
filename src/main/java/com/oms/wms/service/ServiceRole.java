package com.oms.wms.service;

import com.oms.wms.persistence.MapStruct;
import com.oms.wms.persistence.model.Role;
import com.oms.wms.persistence.payload.request.DTORequestRole;
import com.oms.wms.persistence.payload.response.DTOResponseRole;
import com.oms.wms.persistence.repository.RepositoryRole;
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
public class ServiceRole implements ServiceInterface<DTOResponseRole, DTORequestRole> {

    private final RepositoryRole repositoryRole;

    @Override
    public DTOResponseRole create(DTORequestRole created){
        return MapStruct.MAPPER.toDTO(repositoryRole.save(MapStruct.MAPPER.toObject(created)));
    }
    @Override
    public Page<DTOResponseRole> retrieve(Pageable pageable, String value) {
        Role object = new Role();
        ExampleMatcher exampleMatcher = matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        try {
            Method setMethod = object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(pageable.getSort().stream().findFirst().get().getProperty()), String.class);
            setMethod.invoke(object, value);
            Example<Role> example = Example.of(object, exampleMatcher);
            return repositoryRole.findAll(example, pageable).map(MapStruct.MAPPER::toDTO);
        } catch (IllegalArgumentException exception) {
            return repositoryRole.findById(pageable, UUID.fromString(value)).map(MapStruct.MAPPER::toDTO);
        } catch (Exception e) {
            return repositoryRole.findAll(pageable).map(MapStruct.MAPPER::toDTO);
        }
    }
    @Override
    public DTOResponseRole update(UUID id, DTORequestRole updated){
        return MapStruct.MAPPER.toDTO(repositoryRole.save(MapStruct.MAPPER.toObject(updated)));
    }
    @Override
    public DTOResponseRole delete(UUID id){
        DTOResponseRole dtoResponseRole = MapStruct.MAPPER.toDTO(repositoryRole.findById(id).orElse(null));
        repositoryRole.deleteById(id);
        return dtoResponseRole;
    }
    @Override
    public void delete() {
        repositoryRole.deleteAll();
    }

    public boolean existsByName(String value) {
        return repositoryRole.existsByNameIgnoreCase(value);
    }
    public boolean existsByNameAndIdNot(String value, UUID id) {
        return repositoryRole.existsByNameIgnoreCaseAndIdNot(value, id);
    }
}