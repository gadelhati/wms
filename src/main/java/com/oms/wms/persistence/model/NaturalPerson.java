package com.oms.wms.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@PrimaryKeyJoinColumn(name="id")
@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor @Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cpf", "rg"})) @EqualsAndHashCode(callSuper = true)
public class NaturalPerson extends Person {

    private String cpf;
    private String rg;
}