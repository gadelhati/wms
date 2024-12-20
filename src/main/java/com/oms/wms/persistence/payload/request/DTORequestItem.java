package com.oms.wms.persistence.payload.request;

import com.oms.wms.persistence.model.Lot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DTORequestItem {

    private UUID id;
    private String sku;
    @NotNull(message = "{not.null}") @NotBlank(message = "{not.blank}")
    private String gtin;
    private String ncm;
    private String cest;
    private String cfop;
    @NotNull(message = "{not.null}") @NotBlank(message = "{not.blank}")
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
    @NotNull(message = "{not.null}")
    private Lot lot;
}