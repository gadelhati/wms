package com.oms.wms.persistence.payload.response;

import com.oms.wms.persistence.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @AllArgsConstructor
public class DTOResponseOrderTransfer extends DTOResponseOrder {

    private LocalDateTime starts;
    private LocalDateTime finish;
    private Stock stock;//destiny local == internal entity
}