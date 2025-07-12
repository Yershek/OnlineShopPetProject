package com.example.amagazishi.service;

import com.example.amagazishi.entity.ReviewsEntity;

public interface ReviewsService {
    ReviewsEntity addReviews(ReviewsEntity reviews, Long productId);
}
