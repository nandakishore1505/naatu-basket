package com.naatubasket.backend.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequest {

    @NotNull(message = "Product ID is required")
    private Long productId;

    @Min(value = 0)
    private Integer quantity;

    @Min(value = 0)
    private Integer reservedQuantity;

    @Min(value = 0)
    private Integer reorderLevel;

    @Min(value = 0)
    private Integer lowStockThreshold;

}