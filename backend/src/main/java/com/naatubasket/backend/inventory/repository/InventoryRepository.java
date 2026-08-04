package com.naatubasket.backend.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naatubasket.backend.inventory.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProductIdAndDeletedAtIsNull(Long productId);

    boolean existsByProductId(Long productId);

}