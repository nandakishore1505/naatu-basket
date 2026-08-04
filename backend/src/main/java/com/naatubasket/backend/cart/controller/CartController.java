package com.naatubasket.backend.cart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.naatubasket.backend.cart.dto.AddCartItemRequest;
import com.naatubasket.backend.cart.dto.CartResponse;
import com.naatubasket.backend.cart.service.CartService;
import com.naatubasket.backend.common.constants.ApiMessages;
import com.naatubasket.backend.common.constants.ApiPaths;
import com.naatubasket.backend.common.response.ApiResponse;
import com.naatubasket.backend.common.response.ResponseBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPaths.CART)
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<ApiResponse<CartResponse>> addItem(
            @Valid @RequestBody AddCartItemRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(
                        ApiMessages.CART_UPDATED,
                        cartService.addItem(request)));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<CartResponse>> getCart(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        ApiMessages.CART_FETCHED,
                        cartService.getCart(userId)));
    }
}