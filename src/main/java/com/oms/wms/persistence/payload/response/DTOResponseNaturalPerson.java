package com.oms.wms.persistence.payload.response;

import com.oms.wms.persistence.payload.request.DTORequestPerson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class DTOResponseNaturalPerson extends DTORequestPerson {

    private String cpf;
    private String rg;
}