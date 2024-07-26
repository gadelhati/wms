package com.oms.wms.persistence.payload.request;

import com.oms.wms.exception.annotation.UniqueNameCountry;
import lombok.Getter;

@Getter @UniqueNameCountry
public class DTORequestLegalEntity extends DTORequestPerson {

    private String cnpj;
}