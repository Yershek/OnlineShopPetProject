package com.example.amagazishi.mapper;

import com.example.amagazishi.dto.ProductRequestRegister;
import com.example.amagazishi.dto.ProductResponse;
import com.example.amagazishi.dto.ProductResponseMini;
import com.example.amagazishi.entity.ProductEntity;

public class ProductMapper {
    public static ProductEntity toProductEntity(ProductRequestRegister productRequestRegister) {
        return ProductEntity
                .builder()
                .price(productRequestRegister.getPrice())
                .description(productRequestRegister.getDescription())
                .title(productRequestRegister.getTitle())
                .discount(productRequestRegister.getDiscount())
                .compound(productRequestRegister.getCompound())
                .build();
    }

    public static ProductResponseMini toProductResponseMini(ProductEntity productEntity) {
        return ProductResponseMini
                .builder()
                .price(productEntity.getPrice())
                .id(productEntity.getId())
                .title(productEntity.getTitle())
                .discount(productEntity.getDiscount())
                .compound(productEntity.getCompound())
                .build();
    }

    public static ProductResponse  toProductResponse(ProductEntity productEntity) {
        return ProductResponse
                .builder()
                .price(productEntity.getPrice())
                .id(productEntity.getId())
                .title(productEntity.getTitle())
                .discount(productEntity.getDiscount())
                .compound(productEntity.getCompound())
                .images(productEntity.getImage())
                .videos(productEntity.getVideo())
                .reviews(productEntity.getReviews())
                .description(productEntity.getDescription())
                .build();
    }
}