package com.oms.wms.persistence.model;

import com.oms.wms.persistence.GenericAuditEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class Delivery extends GenericAuditEntity {

    private String status;
    private LocalDateTime statusDate;
}