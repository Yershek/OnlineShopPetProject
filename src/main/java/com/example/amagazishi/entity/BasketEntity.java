package com.example.amagazishi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@With
@RequiredArgsConstructor
@Table(name = "baskets")
public class BasketEntity extends BaseEntity {
    @OneToOne(mappedBy = "basket")
    private UserEntity user;
    @ManyToMany
    @JoinTable(
            name = "m2m_baskets_products",
            joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> product;
}
