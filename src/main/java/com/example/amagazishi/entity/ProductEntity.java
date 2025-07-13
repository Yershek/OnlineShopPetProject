package com.example.amagazishi.entity;

import com.example.amagazishi.enums.Active;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@With
@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "products")
public class ProductEntity extends BaseEntity {
    @Column(name = "price")
    private Long price;
    @Column(name = "description")
    private String description;
    @Column(name = "title")
    private String title;
    @Column(name = "compound")
    private String compound;
    @Column(name = "discount", nullable = false)
    private Long discount;
    @Column(name = "active")
    @Enumerated(EnumType.STRING)
    private Active active;
    @ManyToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;
    @ManyToOne
    @JoinColumn(name = "video_id")
    private VideoEntity video;
    @ManyToOne
    @JoinColumn(name = "reviews_id")
    private ReviewsEntity review;
}
