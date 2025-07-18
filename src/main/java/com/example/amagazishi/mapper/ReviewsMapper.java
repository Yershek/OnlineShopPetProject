package com.example.amagazishi.mapper;

import com.example.amagazishi.dto.ReviewsRequest;
import com.example.amagazishi.entity.ReviewsEntity;

public class ReviewsMapper {
    public static ReviewsEntity toReviewsEntity(ReviewsRequest reviewsRequest) {
        return ReviewsEntity
                .builder()
                .description(reviewsRequest.getDescription())
                .build();
    }
}
