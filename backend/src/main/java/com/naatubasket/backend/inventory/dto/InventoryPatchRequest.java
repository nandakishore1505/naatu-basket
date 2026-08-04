package com.naatubasket.backend.inventory.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryPatchRequest {

    @Min(value = 0)
    private Integer quantity;

    @Min(value = 0)
    private Integer reservedQuantity;

    @Min(value = 0)
    private Integer reorderLevel;

    @Min(value = 0)
    private Integer lowStockThreshold;

}