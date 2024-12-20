package com.oms.wms.persistence.model;

import com.oms.wms.persistence.GenericAuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.Collection;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class Stock extends GenericAuditEntity {

    private String name;
    private int maximumBulk;
    @Transient
    private int currentBulk;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "address", nullable = false)
    private Address address;
}