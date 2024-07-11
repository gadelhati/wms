package com.oms.wms.persistence.payload.request;

import com.oms.wms.persistence.model.Delivery;
import com.oms.wms.persistence.model.OrderItem;
import com.oms.wms.persistence.model.Person;
import com.oms.wms.persistence.model.Stock;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Collection;
import java.util.UUID;

@Getter
public class DTORequestOrder {

    private UUID id;
    private String category;
    private float totalCost;
    private Collection<OrderItem> orderItem;
    private Collection<Delivery> delivery;
    @NotNull(message = "{not.null}")
    private Person seller;
    @NotNull(message = "{not.null}")
    private Person buyer;
    private Stock stockSeller;
    private Stock stockBuyer;
}