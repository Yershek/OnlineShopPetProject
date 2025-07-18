package com.example.amagazishi.service;

import com.example.amagazishi.entity.ProductEntity;

import java.util.List;

public interface ProductService{
    List<ProductEntity> findByTitleContaining(String title);
    ProductEntity save(ProductEntity entity);
    ProductEntity update(ProductEntity entity);
    ProductEntity getById(Long id);
    List<ProductEntity> getAll();
}
