package com.oms.wms.persistence.payload.response;

import com.oms.wms.persistence.OrderCategory;
import com.oms.wms.persistence.model.OrderItem;
import com.oms.wms.persistence.model.Person;
import com.oms.wms.persistence.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Getter
public class DTOResponseOrderTransfer extends DTOResponseOrder {

    private LocalDateTime starts;
    private LocalDateTime finish;
    private Stock stock2;//destiny local == internal entity

    public DTOResponseOrderTransfer(UUID id, OrderCategory category, float totalCost, Collection<OrderItem> orderItem, Stock stock, Person person) {
        super(id, category, totalCost, orderItem, stock, person);
    }
}