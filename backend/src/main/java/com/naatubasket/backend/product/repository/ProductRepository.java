package com.naatubasket.backend.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naatubasket.backend.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByDeletedAtIsNull();

    Optional<Product> findByIdAndDeletedAtIsNull(Long id);

    boolean existsBySku(String sku);

    boolean existsByNameIgnoreCaseAndDeletedAtIsNull(String name);

    List<Product> findByCategoryIdAndDeletedAtIsNull(Long categoryId);

}