package com.example.amagazishi.service.impl;

import com.example.amagazishi.entity.ImageEntity;
import com.example.amagazishi.excaption.ImageNotFoundException;
import com.example.amagazishi.repository.ImageRepository;
import com.example.amagazishi.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public ImageEntity getById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("error.isNotFoundImage"));
    }

    @Override
    public ImageEntity getByFileName(String fileName) {
        return imageRepository.findByFileName(fileName).orElseThrow(() -> new ImageNotFoundException("error.isNotFoundImage"));
    }

    @Override
    public ImageEntity save(ImageEntity entity) {
        return imageRepository.save(entity);
    }
}
