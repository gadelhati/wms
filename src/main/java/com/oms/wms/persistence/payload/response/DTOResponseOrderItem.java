package com.oms.wms.persistence.payload.response;

import com.oms.wms.persistence.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseOrderItem {

    private UUID id;
    private float unitPrice;
    private float discount;
    private int quantity;
    private float totalCost;
    private Item item;
}