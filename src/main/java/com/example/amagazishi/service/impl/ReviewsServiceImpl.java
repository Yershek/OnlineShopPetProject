package com.example.amagazishi.service.impl;

import com.example.amagazishi.entity.ProductEntity;
import com.example.amagazishi.entity.ReviewsEntity;
import com.example.amagazishi.repository.ReviewsRepository;
import com.example.amagazishi.service.ProductService;
import com.example.amagazishi.service.ReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository reviewsRepository;
    private final ProductService productService;

    @Override
    public ReviewsEntity addReviews(ReviewsEntity reviews, Long productId) {
        reviewsRepository.save(reviews);
        ProductEntity productEntity = productService.getById(productId);
        List<ReviewsEntity> reviewsEntityList = productEntity.getReviews();
        reviewsEntityList.add(reviews);
        productEntity.setReviews(reviewsEntityList);
        productService.update(productEntity);
        return reviews;
    }

    @Override
    public List<ReviewsEntity> getReviews(Long productId) {
        return productService.getById(productId).getReviews();
    }
}
