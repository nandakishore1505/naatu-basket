package com.naatubasket.backend.cart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.naatubasket.backend.cart.dto.AddCartItemRequest;
import com.naatubasket.backend.cart.dto.CartResponse;
import com.naatubasket.backend.cart.service.CartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<CartResponse> addItem(
            @Valid @RequestBody AddCartItemRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cartService.addItem(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponse> getCart(
            @PathVariable Long userId) {

        return ResponseEntity.ok(cartService.getCart(userId));
    }
}