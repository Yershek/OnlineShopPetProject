package com.example.amagazishi.repository;

import com.example.amagazishi.entity.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailRepository extends JpaRepository<MailEntity, Long> {
    List<MailEntity> findByTitle(String title);
}
