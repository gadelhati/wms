package com.oms.wms.persistence.model;

import com.oms.wms.persistence.GenericAuditEntity;
import com.oms.wms.persistence.OrderCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.Collection;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class Order extends GenericAuditEntity {

    @Enumerated(EnumType.STRING)
    private OrderCategory category;
    @Transient
    private float totalCost;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "order_orderItems", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "orderItem_id", referencedColumnName = "id"))
    private Collection<OrderItem> orderItem;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "stock", nullable = false)
    private Stock stock;
    //local: internal entity stock origin
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "person", nullable = false)
    private Person person;
    //[PURCHASE|SALE] owner: external entity
    //[TRANSFER] holder: internal entity
}