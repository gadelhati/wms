package com.oms.wms.persistence.model;

import com.oms.wms.persistence.GenericAuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor @Table(uniqueConstraints = @UniqueConstraint(columnNames = {"gtin"})) @EqualsAndHashCode(callSuper = true)
public class Product extends GenericAuditEntity {

    private String sku;
    private String gtin;
    private String ncm;//nomenclatura comum do mercosul baseada no sistema harmonizado
    private String cest;//código especificador de substituição tributária
    private String cfop;//código fiscal de operações e de prestações
    private String category;
    private String brand;//marca
    private String model;
    private int minimumStock;//ponto de reposição
    private int maximumStock;
    private int reservedStock;
    private int availableStock;
    private int bulk;//volume
    private int grossWeightMeasurement;//unidade de medida do peso bruto
    private int netWeightMeasurement;//unidade de medida do peso líquido
    private int grossWeight;//peso bruto
    private int netWeight;//peso líquido
    private String url;//image

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "lot", nullable = false)
    private Lot lot;
}