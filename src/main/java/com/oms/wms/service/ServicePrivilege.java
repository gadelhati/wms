package com.oms.wms.service;

import com.oms.wms.persistence.MapStruct;
import com.oms.wms.persistence.model.Privilege;
import com.oms.wms.persistence.payload.request.DTORequestPrivilege;
import com.oms.wms.persistence.payload.response.DTOResponsePrivilege;
import com.oms.wms.persistence.repository.RepositoryPrivilege;
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
public class ServicePrivilege implements ServiceInterface<DTOResponsePrivilege, DTORequestPrivilege> {

    private final RepositoryPrivilege repositoryPrivilege;

    @Override
    public DTOResponsePrivilege create(DTORequestPrivilege created){
        return MapStruct.MAPPER.toDTO(repositoryPrivilege.save(MapStruct.MAPPER.toObject(created)));
    }
    @Override
    public Page<DTOResponsePrivilege> retrieve(Pageable pageable, String value){
        Privilege object = new Privilege();
        ExampleMatcher exampleMatcher = matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        try {
            Method setMethod = object.getClass().getDeclaredMethod("set" + StringUtils.capitalize(pageable.getSort().stream().findFirst().get().getProperty()), String.class);
            setMethod.invoke(object, value);
            Example<Privilege> example = Example.of(object, exampleMatcher);
            return repositoryPrivilege.findAll(example, pageable).map(MapStruct.MAPPER::toDTO);
        } catch (NoSuchMethodException exception) {
            return repositoryPrivilege.findById(pageable, UUID.fromString(value)).map(MapStruct.MAPPER::toDTO);
        } catch (Exception e) {
            return repositoryPrivilege.findAll(pageable).map(MapStruct.MAPPER::toDTO);
        }
    }
    @Override
    public DTOResponsePrivilege update(UUID id, DTORequestPrivilege updated){
        return MapStruct.MAPPER.toDTO(repositoryPrivilege.save(MapStruct.MAPPER.toObject(updated)));
    }
    @Override
    public DTOResponsePrivilege delete(UUID id){
        DTOResponsePrivilege dtoResponsePrivilege = MapStruct.MAPPER.toDTO(repositoryPrivilege.findById(id).orElse(null));
        repositoryPrivilege.deleteById(id);
        return dtoResponsePrivilege;
    }
    @Override
    public void delete() {
        repositoryPrivilege.deleteAll();
    }

    public boolean existsByName(String value) {
        return repositoryPrivilege.existsByNameIgnoreCase(value);
    }
    public boolean existsByNameAndIdNot(String value, UUID id) {
        return repositoryPrivilege.existsByNameIgnoreCaseAndIdNot(value, id);
    }
}