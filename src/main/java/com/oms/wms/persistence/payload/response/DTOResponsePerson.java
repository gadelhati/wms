package com.oms.wms.persistence.payload.response;

import com.oms.wms.persistence.model.Address;
import com.oms.wms.persistence.model.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponsePerson {

    private UUID id;
    private String name;
    private LocalDateTime birth;
    private String email;
    private Collection<String> telephone;
    private Country country;
    private Collection<Address> address;
}