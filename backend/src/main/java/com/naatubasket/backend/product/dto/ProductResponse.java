package com.naatubasket.backend.product.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Long id;

    private Long categoryId;

    private String categoryName;

    private String name;

    private String description;

    private String sku;

    private String barcode;

    private String brand;

    private String unit;

    private BigDecimal mrp;

    private BigDecimal sellingPrice;

    private String imageUrl;

    private Boolean active;
}