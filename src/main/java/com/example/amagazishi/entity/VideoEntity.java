package com.example.amagazishi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@With
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "videos")
public class VideoEntity extends BaseEntity {
    @Column(name = "file_name")
    private String fileName;

    public VideoEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}
