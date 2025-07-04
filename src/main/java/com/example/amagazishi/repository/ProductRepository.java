package com.example.amagazishi.repository;

import com.example.amagazishi.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByTitleContaining(String title);
}
