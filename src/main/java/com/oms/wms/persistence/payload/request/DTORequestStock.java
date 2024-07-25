package com.oms.wms.persistence.payload.request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class DTORequestStock {

    private UUID id;
    private int maximumBulk;
    private int currentBulk;
}