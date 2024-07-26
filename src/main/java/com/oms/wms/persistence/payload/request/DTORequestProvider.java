package com.oms.wms.persistence.payload.request;

import com.oms.wms.exception.annotation.UniqueNameCountry;
import com.oms.wms.persistence.model.Stock;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Collection;

@Getter @UniqueNameCountry
public class DTORequestProvider extends DTORequestLegalEntity {

    private LocalTime deliveryTime;
    private int cost;
    private int quality;
    private int paymentTerms;
    private int flexibility;
    private int service;
    private Collection<Stock> stock;
}