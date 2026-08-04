package com.naatubasket.backend.cart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.naatubasket.backend.cart.dto.CartItemResponse;
import com.naatubasket.backend.cart.entity.CartItem;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    CartItemResponse toResponse(CartItem cartItem);

}