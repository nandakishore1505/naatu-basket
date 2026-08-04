package com.naatubasket.backend.cart.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naatubasket.backend.auth.entity.User;
import com.naatubasket.backend.auth.repository.UserRepository;
import com.naatubasket.backend.cart.dto.AddCartItemRequest;
import com.naatubasket.backend.cart.dto.CartItemResponse;
import com.naatubasket.backend.cart.dto.CartResponse;
import com.naatubasket.backend.cart.entity.Cart;
import com.naatubasket.backend.cart.entity.CartItem;
import com.naatubasket.backend.cart.mapper.CartMapper;
import com.naatubasket.backend.cart.repository.CartItemRepository;
import com.naatubasket.backend.cart.repository.CartRepository;
import com.naatubasket.backend.common.exception.ResourceNotFoundException;
import com.naatubasket.backend.product.entity.Product;
import com.naatubasket.backend.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    public CartResponse addItem(AddCartItemRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found."));

        Cart cart = cartRepository
                .findByUserIdAndStatus(user.getId(), "ACTIVE")
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .user(user)
                            .status("ACTIVE")
                            .build();
                    return cartRepository.save(newCart);
                });

        CartItem item = cartItemRepository
                .findByCartIdAndProductId(cart.getId(), product.getId())
                .orElse(null);

        if (item == null) {

            item = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .price(product.getSellingPrice())
                    .total(product.getSellingPrice()
                            .multiply(BigDecimal.valueOf(request.getQuantity())))
                    .build();

        } else {

            item.setQuantity(item.getQuantity() + request.getQuantity());

            item.setTotal(
                    item.getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        cartItemRepository.save(item);

        return getCart(user.getId());
    }

    @Transactional(readOnly = true)
    public CartResponse getCart(Long userId) {

        Cart cart = cartRepository
                .findByUserIdAndStatus(userId, "ACTIVE")
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart not found."));

        List<CartItemResponse> items = cartItemRepository
                .findByCartId(cart.getId())
                .stream()
                .map(cartMapper::toResponse)
                .toList();

        BigDecimal grandTotal = items.stream()
                .map(CartItemResponse::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        CartResponse response = new CartResponse();

        response.setCartId(cart.getId());
        response.setUserId(userId);
        response.setItems(items);
        response.setGrandTotal(grandTotal);

        return response;
    }
}