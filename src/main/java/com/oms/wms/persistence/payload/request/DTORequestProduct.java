package com.oms.wms.persistence.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DTORequestProduct {

    private UUID id;
    private String gtin;
    @NotNull(message = "{not.null}") @NotBlank(message = "{not.blank}")
    private String category;
    private String brand;
    private String model;
    private int minimumStock;
    private int maximumStock;
    private int reservedStock;
    private int availableStock;
    private int bulk;
}