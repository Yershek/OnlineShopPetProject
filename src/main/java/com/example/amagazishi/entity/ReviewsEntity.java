package com.example.amagazishi.entity;

import jakarta.persistence.Column;
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
    @Column(name = "description")
    public String description;
}
