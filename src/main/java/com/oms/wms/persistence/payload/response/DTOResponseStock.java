package com.oms.wms.persistence.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseStock {

    private UUID id;
    private int maximumBulk;
    private int currentBulk;
}