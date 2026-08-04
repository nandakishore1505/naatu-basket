package com.naatubasket.backend.product.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPatchRequest {

    private Long categoryId;

    @Size(max = 200)
    private String name;

    @Size(max = 1000)
    private String description;

    private String sku;

    private String barcode;

    private String brand;

    private String unit;

    @DecimalMin("0.01")
    private BigDecimal mrp;

    @DecimalMin("0.01")
    private BigDecimal sellingPrice;

    private String imageUrl;

    private Boolean active;
}