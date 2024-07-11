package com.oms.wms.persistence.payload.response;

import com.oms.wms.persistence.model.State;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseCity {

    private UUID id;
    private String code;
    private String name;
    private State state;
}