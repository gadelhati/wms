package com.oms.wms.persistence.model;

import com.oms.wms.persistence.GenericAuditEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor @Table(uniqueConstraints = @UniqueConstraint(columnNames = {"code", "name"})) @EqualsAndHashCode(callSuper = true)
public class Country extends GenericAuditEntity {

    private String code;
    private String name;
}