package com.example.amagazishi.service.impl;

import com.example.amagazishi.exception.FileNameDoubleException;
import com.example.amagazishi.service.MinIoService;
import com.example.amagazishi.service.VideoFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class VideoFileServiceImpl implements VideoFileService {
    private final MinIoService minIoService;

    @Value("${minio.bucket.name.video}")
    private String bucketName;

    @Override
    public InputStream getByFileName(String fileName) {
        return minIoService.streamFile(bucketName, fileName);
    }

    @Override
    public void save(MultipartFile file) {
        if (minIoService.fileExists(bucketName, file.getOriginalFilename())) {
            throw new FileNameDoubleException("error.doubleName");
        }
        minIoService.upload(file, bucketName);
    }

    @Override
    public String getContentType(String fileName) {
        return minIoService.getContentType(bucketName, fileName);
    }
}
