package com.oms.wms.persistence.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponsePrivilege {

    private UUID id;
    private String name;
//    private Set<Role> role = new HashSet<>();
}