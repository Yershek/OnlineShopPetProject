package com.example.amagazishi.controller;

import com.example.amagazishi.entity.VideoEntity;
import com.example.amagazishi.service.VideoFileService;
import com.example.amagazishi.service.VideoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class VideoController {

    private final VideoService videoService;
    private final VideoFileService videoFileService;

    @PostMapping(name = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VideoEntity> save(@RequestParam("file") MultipartFile file) {
        videoFileService.save(file);
        VideoEntity entity = VideoEntity
                .builder()
                .fileName(file.getOriginalFilename())
                .build();
        return ResponseEntity.ok(videoService.save(entity));
    }

    @GetMapping("/get-file-by-id/{id}")
    public ResponseEntity<InputStreamResource> getFileById(@PathVariable("id") Long id) {
        String fileName = videoService.getById(id).getFileName();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(videoFileService.getContentType(fileName)))
                .body(new InputStreamResource(videoFileService.getByFileName(fileName)));
    }

    @GetMapping("/get-file-by-file-name")
    public ResponseEntity<InputStreamResource> getFileByFileName(@RequestParam("fileName") String fileName) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(videoFileService.getContentType(fileName)))
                .body(new InputStreamResource(videoFileService.getByFileName(fileName)));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<VideoEntity> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(videoService.getById(id));
    }

    @GetMapping("/get-by-file-name")
    public ResponseEntity<VideoEntity> getByFileName(@RequestParam("fileName") String fileName) {
        return ResponseEntity.ok(videoService.getByFileName(fileName));
    }
}
