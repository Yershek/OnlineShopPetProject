package com.example.amagazishi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@With
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "mails")
public class MailEntity extends BaseEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "email_to")
    private String emailTo;
    @Column(name = "date_created")
    private LocalDate dateCreated;

    public MailEntity() {
        dateCreated = LocalDate.now();
    }
}
