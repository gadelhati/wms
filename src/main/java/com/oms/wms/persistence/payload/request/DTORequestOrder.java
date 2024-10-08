package com.oms.wms.persistence.payload.request;

import com.oms.wms.persistence.OrderCategory;
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
    private OrderCategory category;
    private float totalCost;
    private Collection<OrderItem> orderItem;
    @NotNull(message = "{not.null}")
    private Stock stock;
    @NotNull(message = "{not.null}")
    private Person person;
}