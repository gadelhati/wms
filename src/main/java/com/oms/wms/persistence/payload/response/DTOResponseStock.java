package com.oms.wms.persistence.payload.response;

import com.oms.wms.persistence.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseStock {

    private UUID id;
    private int maximumBulk;
    private int currentBulk;
    private Address address;
}