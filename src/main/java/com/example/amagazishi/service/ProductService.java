package com.example.amagazishi.service;

import com.example.amagazishi.entity.ProductEntity;

import java.util.List;

public interface ProductService extends BaseService<ProductEntity> {
    List<ProductEntity> findByTitleContaining(String title);
}
