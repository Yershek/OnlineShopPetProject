package com.example.amagazishi.dto;

import com.example.amagazishi.entity.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

import java.util.List;

@With
@Getter
@Setter
@Builder
public class BasketResponse {
    private List<ProductResponseMini> productList;
}
