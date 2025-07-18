package com.example.amagazishi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

@With
@Getter
@Setter
@Builder
public class ReviewsRequest {
    private String description;
}
