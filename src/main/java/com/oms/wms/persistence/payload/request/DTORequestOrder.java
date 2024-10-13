package com.oms.wms.persistence.payload.request;

import com.oms.wms.exception.annotation.ExistsEnumOrderCategory;
import com.oms.wms.exception.annotation.ExistsOrderItem;
import com.oms.wms.exception.annotation.ExistsPerson;
import com.oms.wms.exception.annotation.ExistsStock;
import com.oms.wms.persistence.OrderCategory;
import com.oms.wms.persistence.model.OrderItem;
import com.oms.wms.persistence.model.Person;
import com.oms.wms.persistence.model.Stock;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Collection;
import java.util.UUID;

@Getter @ExistsStock @ExistsPerson @ExistsOrderItem
public class DTORequestOrder {

    private UUID id;
    @NotNull(message = "{not.null}") @NotBlank(message = "{not.blank}")
    @ExistsEnumOrderCategory(enumClass = OrderCategory.class)
    private String category;
    private float totalCost;
    private Collection<OrderItem> orderItem;
    @NotNull(message = "{not.null}")
    private Stock stock;
    @NotNull(message = "{not.null}")
    private Person person;
}