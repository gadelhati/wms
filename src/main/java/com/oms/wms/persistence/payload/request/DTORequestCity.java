package com.oms.wms.persistence.payload.request;

import com.oms.wms.exception.annotation.UniqueCodeCity;
import com.oms.wms.persistence.model.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter @UniqueCodeCity
public class DTORequestCity {

    private UUID id;
    @NotNull(message = "{not.null}") @NotBlank(message = "{not.blank}")
    private String code;
    @NotNull(message = "{not.null}") @NotBlank(message = "{not.blank}")
    private String name;
    private State state;
}