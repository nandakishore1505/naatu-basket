package com.naatubasket.backend.cart.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponse {

    private Long id;

    private Long productId;

    private String productName;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal total;

}