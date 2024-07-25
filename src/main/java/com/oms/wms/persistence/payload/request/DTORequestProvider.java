package com.oms.wms.persistence.payload.request;

import com.oms.wms.exception.annotation.UniqueNameCountry;
import com.oms.wms.persistence.model.Address;
import com.oms.wms.persistence.model.Country;
import com.oms.wms.persistence.model.Stock;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.UUID;

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