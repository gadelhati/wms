package com.oms.wms.persistence.payload.request;

import com.oms.wms.persistence.model.Address;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DTORequestStock {

    private UUID id;
    private int maximumBulk;
    private int currentBulk;
    private Address address;
}