package com.example.amagazishi.service;

import com.example.amagazishi.entity.ReviewsEntity;

import java.util.List;

public interface ReviewsService {
    ReviewsEntity addReviews(ReviewsEntity reviews, Long productId);
    List<ReviewsEntity> getReviews(Long productId);
}
