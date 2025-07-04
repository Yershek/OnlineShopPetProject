package com.example.amagazishi.repository;

import com.example.amagazishi.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
    Optional<VideoEntity> findByFileName(String fileName);
}
