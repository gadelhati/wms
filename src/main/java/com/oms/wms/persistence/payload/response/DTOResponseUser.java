package com.oms.wms.persistence.payload.response;

import com.oms.wms.persistence.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseUser {

    private UUID id;
    private String username;
    private String email;
    private String password;
    private Boolean active;
    private Collection<Role> role;
}