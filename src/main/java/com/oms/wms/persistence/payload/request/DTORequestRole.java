package com.oms.wms.persistence.payload.request;

import com.oms.wms.exception.annotation.UniqueNameRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter @UniqueNameRole
public class DTORequestRole {

    private UUID id;
    @NotNull(message = "{not.null}") @NotBlank(message = "{not.blank}")
    private String name;
//    private Set<Privilege> privileges = new HashSet<>();
}