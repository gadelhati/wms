package com.oms.wms.persistence.payload.request;

import com.oms.wms.persistence.model.Order;
import com.oms.wms.persistence.model.Stock;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DTORequestOrderTransfer extends Order {

    private LocalDateTime starts;
    private LocalDateTime finish;
    private Stock stock;//destiny local == internal entity
}