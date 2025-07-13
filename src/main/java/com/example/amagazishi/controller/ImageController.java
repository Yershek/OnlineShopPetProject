package com.example.amagazishi.controller;

import com.example.amagazishi.entity.ImageEntity;
import com.example.amagazishi.service.ImageFileService;
import com.example.amagazishi.service.ImageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final ImageFileService imageFileService;

    @PostMapping("/save-images")
    public ResponseEntity<ImageEntity> save(@RequestParam("image") MultipartFile image) {
        imageFileService.save(image);
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setFilename(image.getOriginalFilename());
        return ResponseEntity.ok(imageService.save(imageEntity));
    }

    @GetMapping("/get-file-by-id/{id}")
    public ResponseEntity<InputStreamResource> getFileById(@PathVariable("id") Long id) {
        String fileName = imageService.getById(id).getFilename();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageFileService.getContentType(fileName)))
                .body(new InputStreamResource(imageFileService.getByFileName(fileName)));
    }

    @GetMapping("/get-file-by-file-name")
    public ResponseEntity<InputStreamResource> getFileByFileName(@RequestParam("fileName") String fileName) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageFileService.getContentType(fileName)))
                .body(new InputStreamResource(imageFileService.getByFileName(fileName)));
    }

    @GetMapping("/get-by-id/{id}")
    private ResponseEntity<ImageEntity> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(imageService.getById(id));
    }

    @GetMapping("/get-by-file-name")
    private ResponseEntity<ImageEntity> getByFileName(@RequestParam("fileName") String fileName) {
        return ResponseEntity.ok(imageService.getByFileName(fileName));
    }
}
