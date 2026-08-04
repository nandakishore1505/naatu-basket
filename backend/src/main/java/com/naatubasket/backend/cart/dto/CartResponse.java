package com.naatubasket.backend.cart.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponse {

    private Long cartId;

    private Long userId;

    private List<CartItemResponse> items;

    private BigDecimal grandTotal;

}