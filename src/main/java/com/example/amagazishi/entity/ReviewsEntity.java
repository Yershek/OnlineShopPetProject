package com.example.amagazishi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@With
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "reviews")
public class ReviewsEntity extends BaseEntity {
    public String description;
}
