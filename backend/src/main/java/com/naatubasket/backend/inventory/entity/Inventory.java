package com.naatubasket.backend.inventory.entity;

import com.naatubasket.backend.common.entity.BaseEntity;
import com.naatubasket.backend.product.entity.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Default
    @Column(nullable = false)
    private Integer quantity = 0;

    @Default
    @Column(name = "reserved_quantity", nullable = false)
    private Integer reservedQuantity = 0;

    @Default
    @Column(name = "reorder_level", nullable = false)
    private Integer reorderLevel = 10;

    @Default
    @Column(name = "low_stock_threshold", nullable = false)
    private Integer lowStockThreshold = 5;

    @Version
    @Column(nullable = false)
    private Integer version = 0;

    @PrePersist
    public void prePersist() {

        if (quantity == null) {
            quantity = 0;
        }

        if (reservedQuantity == null) {
            reservedQuantity = 0;
        }

        if (reorderLevel == null) {
            reorderLevel = 10;
        }

        if (lowStockThreshold == null) {
            lowStockThreshold = 5;
        }
    }
}