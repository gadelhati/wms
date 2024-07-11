package com.oms.wms.persistence.payload.request;

import com.oms.wms.exception.annotation.UniqueCodeState;
import com.oms.wms.persistence.model.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter @UniqueCodeState
public class DTORequestState {

    private UUID id;
    @NotNull(message = "{not.null}") @NotBlank(message = "{not.blank}")
    private String code;
    @NotNull(message = "{not.null}") @NotBlank(message = "{not.blank}")
    private String name;
    private Country country;
}