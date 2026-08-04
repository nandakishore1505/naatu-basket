package com.naatubasket.backend.product.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    @NotNull(message = "Category is required")
    private Long categoryId;

    @NotBlank(message = "Product name is required")
    @Size(max = 200)
    private String name;

    @Size(max = 1000)
    private String description;

    @NotBlank(message = "SKU is required")
    private String sku;

    private String barcode;

    private String brand;

    @NotBlank(message = "Unit is required")
    private String unit;

    @NotNull(message = "MRP is required")
    @DecimalMin("0.01")
    private BigDecimal mrp;

    @NotNull(message = "Selling price is required")
    @DecimalMin("0.01")
    private BigDecimal sellingPrice;

    private String imageUrl;

    private Boolean active;
}