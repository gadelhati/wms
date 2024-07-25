package com.oms.wms.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@PrimaryKeyJoinColumn(name="id")
@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor @Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cnpj"})) @EqualsAndHashCode(callSuper = true)
public class LegalEntity extends Person {

    private String cnpj;
}