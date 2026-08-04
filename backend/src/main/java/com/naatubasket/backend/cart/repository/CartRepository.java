package com.naatubasket.backend.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naatubasket.backend.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserIdAndStatus(Long userId, String status);

}