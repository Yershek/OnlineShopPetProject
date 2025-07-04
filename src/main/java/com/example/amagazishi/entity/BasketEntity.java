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
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "product_id")
    private List<ProductEntity> product;
}
