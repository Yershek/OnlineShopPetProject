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
public class ProductEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    private String description;
    private String title;
    private String compound;
    private Long discount;
    private Active active;
    @ManyToMany
    @JoinTable(
            name = "m2m_product_images",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "images_id")
    )
    private List<ImageEntity> images;
    @ManyToMany
    @JoinTable(
            name = "m2m_product_video",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private List<VideoEntity> videos;
    @ManyToMany
    @JoinTable(
            name = "m2m_product_reviews",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "reviews_id")
    )
    private List<ReviewsEntity> reviews;
}
