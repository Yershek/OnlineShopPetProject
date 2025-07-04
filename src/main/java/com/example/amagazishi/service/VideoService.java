package com.example.amagazishi.service;

import com.example.amagazishi.entity.VideoEntity;

public interface VideoService {
    VideoEntity getById(Long id);
    VideoEntity getByFileName(String fileName);
    VideoEntity save(VideoEntity entity);
}
