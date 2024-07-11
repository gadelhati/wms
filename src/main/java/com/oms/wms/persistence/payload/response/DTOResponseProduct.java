package com.oms.wms.persistence.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseProduct {

    private UUID id;
    private String gtin;
    private String category;
    private String brand;
    private String model;
    private int minimumStock;
    private int maximumStock;
    private int reservedStock;
    private int availableStock;
    private int bulk;
}