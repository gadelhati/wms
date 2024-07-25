package com.oms.wms.persistence.payload.response;

import com.oms.wms.persistence.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Collection;

@Getter @AllArgsConstructor
public class DTOResponseProvider extends DTOResponseLegalEntity {

    private LocalTime deliveryTime;
    private int cost;
    private int quality;
    private int paymentTerms;
    private int flexibility;
    private int service;
    private Collection<Stock> stock;
}