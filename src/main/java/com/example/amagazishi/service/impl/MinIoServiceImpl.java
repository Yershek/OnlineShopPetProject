package com.example.amagazishi.service.impl;

import com.example.amagazishi.service.MinIoService;
import io.minio.*;
import io.minio.errors.ErrorResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MinIoServiceImpl implements MinIoService {
    private final MinioClient minioClient;

    @Override
    public void upload(MultipartFile file, String bucketName){
        try {
            ensureBucketExists(bucketName);
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(file.getOriginalFilename())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public InputStream streamFile(String bucketName, String fileName){
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean fileExists(String bucketName, String fileName){
        try {
            minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
            return true;
        } catch (ErrorResponseException e) {
            if (e.errorResponse().code().equals("NoSuchKey")) {
                return false;
            } else {
                throw new RuntimeException(e);
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getContentType(String bucketName, String fileName){
        try {
            StatObjectResponse stat = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
            return stat.contentType();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void ensureBucketExists(String bucketName){
        try {
            boolean found = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucketName).build()
            );
            if (!found) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(bucketName).build()
                );
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
