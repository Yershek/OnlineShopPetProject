package com.example.amagazishi.service;

import com.example.amagazishi.entity.BasketEntity;
import com.example.amagazishi.entity.ProductEntity;

public interface BasketService {
    BasketEntity addProductInBasket(ProductEntity product);
    BasketEntity getBasket();
    BasketEntity deleteProductId(Long id);
}
