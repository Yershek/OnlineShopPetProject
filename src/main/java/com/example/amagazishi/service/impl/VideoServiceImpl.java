package com.example.amagazishi.service.impl;

import com.example.amagazishi.entity.VideoEntity;
import com.example.amagazishi.excaption.VideoNotFoundException;
import com.example.amagazishi.repository.VideoRepository;
import com.example.amagazishi.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;

    @Override
    public VideoEntity getById(Long id) {
        return videoRepository.findById(id).orElseThrow(() -> new VideoNotFoundException("error.isNotFoundVideo"));
    }

    @Override
    public VideoEntity getByFileName(String fileName) {
        return videoRepository.findByFileName(fileName).orElseThrow(() -> new VideoNotFoundException("error.isNotFoundVideo"));
    }

    @Override
    public VideoEntity save(VideoEntity entity) {
        return videoRepository.save(entity);
    }
}
