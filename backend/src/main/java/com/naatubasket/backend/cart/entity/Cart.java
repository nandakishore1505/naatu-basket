package com.naatubasket.backend.cart.entity;

import java.util.ArrayList;
import java.util.List;

import com.naatubasket.backend.auth.entity.User;
import com.naatubasket.backend.common.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder.Default
    @Column(nullable = false)
    private String status = "ACTIVE";

    @OneToMany(mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    private List<CartItem> items = new ArrayList<>();

}