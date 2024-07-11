package com.oms.wms.persistence.payload.request;

import com.oms.wms.exception.annotation.UniqueNamePrivilege;
import com.oms.wms.persistence.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter @UniqueNamePrivilege
public class DTORequestPrivilege {

    private UUID id;
    @NotNull(message = "{not.null}") @NotBlank(message = "{not.blank}")
    private String name;
    private Set<Role> roles = new HashSet<>();
}