package com.example.amagazishi.controller;

import com.example.amagazishi.entity.ReviewsEntity;
import com.example.amagazishi.service.ReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewsController {

    private final ReviewsService reviewsService;

    @PostMapping("/add-reviews/{id}")
    public ResponseEntity<ReviewsEntity> addReviews(@RequestBody ReviewsEntity reviewsEntity, @PathVariable("id") Long id) {
        return ResponseEntity.ok(reviewsService.addReviews(reviewsEntity, id));
    }

    @GetMapping("/get-reviews-product-id/{id}")
    public ResponseEntity<List<ReviewsEntity>> getReviewsProductId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reviewsService.getReviews(id));
    }
}
