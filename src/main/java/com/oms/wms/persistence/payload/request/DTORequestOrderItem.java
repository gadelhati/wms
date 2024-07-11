package com.oms.wms.persistence.payload.request;

import com.oms.wms.persistence.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DTORequestOrderItem {

    private UUID id;
    private float unitPrice;
    private float discount;
    @NotNull(message = "{not.null}")
    private int quantity;
    private float totalCost;
    @NotNull(message = "{not.null}")
    private Product product;
}