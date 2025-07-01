package com.example.amagazishi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class MailEntity extends BaseEntity {
    private String title;
    private String content;
    private String emailTo;
    private LocalDate dateCreated;

    @PrePersist
    public void prePersist() {
        this.dateCreated = LocalDate.now();
    }
}
