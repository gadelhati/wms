package com.oms.wms.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.time.LocalTime;
import java.util.Collection;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor @Table @EqualsAndHashCode(callSuper = true)
public class Provider extends LegalEntity {

    private LocalTime deliveryTime;
    private int cost;
    private int quality;
    private int paymentTerms;
    private int flexibility;
    private int service;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "provider_stocks", joinColumns = @JoinColumn(name = "provider_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id", referencedColumnName = "id"))
    private Collection<Stock> stock;
}