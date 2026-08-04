package com.naatubasket.backend.inventory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryResponse {

    private Long id;

    private Long productId;

    private String productName;

    private Integer quantity;

    private Integer reservedQuantity;

    private Integer reorderLevel;

    private Integer lowStockThreshold;

}