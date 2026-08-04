package com.naatubasket.backend.cart.entity;

import java.math.BigDecimal;

import com.naatubasket.backend.common.entity.BaseEntity;
import com.naatubasket.backend.product.entity.Product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @PrePersist
    @PreUpdate
    public void calculateTotal() {
        if (price != null && quantity != null) {
            total = price.multiply(BigDecimal.valueOf(quantity));
        }
    }

}