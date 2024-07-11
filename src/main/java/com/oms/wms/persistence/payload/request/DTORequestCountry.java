package com.oms.wms.persistence.payload.request;

import com.oms.wms.exception.annotation.UniqueNameCountry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter @UniqueNameCountry
public class DTORequestCountry {

    private UUID id;
    @NotNull(message = "{not.null}") @NotBlank(message = "{not.blank}")
    private String name;
}