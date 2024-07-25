package com.oms.wms.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum OrderCategory {
    PURCHASE("PURCHASE"),
    SALE("SALE"),
    TRANSFER("TRANSFER");

    private final String category;
}