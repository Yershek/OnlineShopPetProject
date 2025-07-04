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
    private Integer discount;
    @Column(name = "image_id")
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> image;
    @Column(name = "video_id")
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoEntity> video;
    @Column(name = "active")
    @Enumerated(EnumType.STRING)
    private Active active;
    @Column(name = "reviews_id")
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewsEntity> reviews;
}
