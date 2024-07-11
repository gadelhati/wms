package com.oms.wms.persistence.model;

import com.oms.wms.persistence.GenericAuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.Collection;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper = false)
public class Order extends GenericAuditEntity {

    private String category;
    @Transient
    private float totalCost;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "order_orderItems", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "orderItem_id", referencedColumnName = "id"))
    private Collection<OrderItem> orderItem;
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "order_deliveries", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "delivery_id", referencedColumnName = "id"))
//    private Collection<Delivery> delivery;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "addressSeller", nullable = false)
    private Address addressSeller;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "addressBuyer", nullable = false)
    private Address addressBuyer;
}