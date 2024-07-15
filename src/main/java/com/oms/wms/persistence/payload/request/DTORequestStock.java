package com.oms.wms.persistence.payload.request;

import com.oms.wms.persistence.model.Order;
import lombok.Getter;

import java.util.Collection;
import java.util.UUID;

@Getter
public class DTORequestStock {

    private UUID id;
    private int maximumBulk;
    private int currentBulk;
    private Collection<Order> order;
}