package com.oms.wms.persistence.payload.response;

import com.oms.wms.persistence.model.City;
import com.oms.wms.persistence.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseAddress {

    private UUID id;
    private String cepNumber;
    private String cepCategory;
    private String cepSubCategory;
    private String neighborhood;
    private String address;
    private String complement;
    private String IBGECode;
    private City city;
}