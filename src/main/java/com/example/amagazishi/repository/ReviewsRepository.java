package com.example.amagazishi.repository;

import com.example.amagazishi.entity.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Long> {
}
