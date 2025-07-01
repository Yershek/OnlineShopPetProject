package com.example.amagazishi.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class MailEntity extends BaseEntity {
    private String title;
    private String content;
    private String emailTo;
    private LocalDate dateCreated;

    public MailEntity() {
        dateCreated = LocalDate.now();
    }
}
