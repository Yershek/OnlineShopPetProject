package com.example.amagazishi.mapper;

import com.example.amagazishi.dto.BasketResponse;
import com.example.amagazishi.entity.BasketEntity;

public class BasketMapper {
    public static BasketResponse toBasketResponse(BasketEntity basketEntity) {
        return BasketResponse
                .builder()
                .productList(basketEntity.getProduct().stream().map(ProductMapper::toProductResponseMini).toList())
                .build();
    }
}
