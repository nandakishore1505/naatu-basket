package com.naatubasket.backend.cart.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCartItemRequest {

    @Min(1)
    private Integer quantity;

}