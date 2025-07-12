package com.example.amagazishi.dto;

import com.example.amagazishi.entity.ImageEntity;
import com.example.amagazishi.entity.ReviewsEntity;
import com.example.amagazishi.entity.VideoEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

import java.util.List;

@With
@Getter
@Setter
@Builder
public class ProductResponse {
    private Long id;
    private Long price;
    private String title;
    private String compound;
    private Integer discount;
    private String description;
    private List<ImageEntity> images;
    private List<VideoEntity> videos;
    private List<ReviewsEntity> reviews;

}
