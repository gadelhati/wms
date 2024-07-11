package com.oms.wms.persistence.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseDelivery {

    private UUID id;
    private String status;
    private LocalDateTime statusDate;
}