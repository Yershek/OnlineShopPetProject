package com.example.amagazishi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ImageFileService {
    InputStream getByFileName(String fileName);
    void save(MultipartFile file);
    String getContentType(String fileName);
}
