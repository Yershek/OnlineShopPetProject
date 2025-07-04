package com.example.amagazishi.service;

import com.example.amagazishi.entity.ImageEntity;

public interface ImageService {
    ImageEntity getById(Long id);
    ImageEntity getByFileName(String fileName);
    ImageEntity save(ImageEntity entity);
}
