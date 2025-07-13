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
    @OneToMany(mappedBy = "basket_id")
    @Column(name = "product_id")
    private List<ProductEntity> product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public BasketEntity addProduct(ProductEntity product) {
        this.product.add(product);
        return this;
    }

    public BasketEntity removeProduct(ProductEntity product) {
        this.product.remove(product);
        return this;
    }
}
