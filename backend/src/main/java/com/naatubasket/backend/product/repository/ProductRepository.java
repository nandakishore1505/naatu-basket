package com.naatubasket.backend.product.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.naatubasket.backend.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndDeletedAtIsNull(Long id);

    boolean existsBySku(String sku);

    Page<Product> findByDeletedAtIsNull(Pageable pageable);

    Page<Product> findByNameContainingIgnoreCaseAndDeletedAtIsNull(
            String keyword,
            Pageable pageable);

}