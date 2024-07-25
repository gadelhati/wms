package com.oms.wms.persistence.payload.response;

import com.oms.wms.persistence.model.Delivery;
import com.oms.wms.persistence.model.OrderItem;
import com.oms.wms.persistence.model.Person;
import com.oms.wms.persistence.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseOrder {

    private UUID id;
    private String category;
    private float totalCost;
    private Collection<OrderItem> orderItem;
    private Person person;
    private Stock stock;
}