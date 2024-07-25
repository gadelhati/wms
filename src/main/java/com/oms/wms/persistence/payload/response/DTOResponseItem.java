package com.oms.wms.persistence.payload.response;

import com.oms.wms.persistence.model.Lot;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseItem {

    private UUID id;
    private String sku;
    private String gtin;
    private String category;
    private String brand;
    private String model;
    private int minimumStock;
    private int maximumStock;
    private int reservedStock;
    private int availableStock;
    private int bulk;
    private int grossWeightMeasurement;//unidade de medida do peso bruto
    private int netWeightMeasurement;//unidade de medida do peso líquido
    private int grossWeight;//peso bruto
    private int netWeight;//peso líquido
    private String url;//image
    private Lot lot;
}