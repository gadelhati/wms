package com.oms.wms.persistence.payload.request;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class DTORequestLot {

    private UUID id;
    private String number;
    private LocalDateTime manufacturing;
    private LocalDateTime overdue;
}