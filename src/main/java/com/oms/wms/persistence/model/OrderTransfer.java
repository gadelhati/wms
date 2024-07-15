package com.oms.wms.persistence.model;

import com.oms.wms.persistence.GenericAuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper = false)
public class OrderTransfer extends Order {

    private LocalDateTime starts;
    private LocalDateTime finish;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "stock", nullable = false)
    private Stock stock;//destine local, internal entity
}