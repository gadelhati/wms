package com.oms.wms.persistence.payload.request;

import com.oms.wms.exception.annotation.UniqueNameCountry;
import com.oms.wms.persistence.model.Address;
import com.oms.wms.persistence.model.Country;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Getter @UniqueNameCountry
public class DTORequestNaturalPerson extends DTORequestPerson {

    private String cpf;
    private String rg;
}