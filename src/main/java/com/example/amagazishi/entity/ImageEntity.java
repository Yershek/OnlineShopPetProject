package com.example.amagazishi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@With
@RequiredArgsConstructor
@Table(name = "images")
public class ImageEntity extends BaseEntity {
    @Column(name = "file_name")
    private String fileName;

    public ImageEntity setFilename(String filename) {
        this.fileName = filename;
        return this;
    }
}
