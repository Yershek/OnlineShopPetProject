package com.example.amagazishi.service.impl;

import com.example.amagazishi.entity.BaseEntity;
import com.example.amagazishi.entity.ProductEntity;
import com.example.amagazishi.enums.Active;
import com.example.amagazishi.repository.ProductRepository;
import com.example.amagazishi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    public final ProductRepository productRepository;

    @Override
    public List<ProductEntity> findByTitleContaining(String title) {
        return productRepository.findByTitleContaining(title);
    }

    @Override
    public ProductEntity save(ProductEntity entity) {
        return productRepository.save(entity);
    }

    @Override
    public ProductEntity update(ProductEntity entity) {
        ProductEntity old = productRepository.findById(entity.getId())
                .orElseThrow(()->new RuntimeException("error.NotFoundProduct"));
        if(old.getActive().equals(Active.DELETE)){
            throw new RuntimeException("error.ProductDeleted");
        }
        old.setTitle(entity.getTitle());
        old.setDescription(entity.getDescription());
        old.setPrice(entity.getPrice());
        old.setVideo(entity.getVideo());
        old.setDiscount(entity.getDiscount());
        old.setCompound(entity.getCompound());
        old.setImage(entity.getImage());
        old.setReviews(entity.getReviews());
        old.setActive(entity.getActive());
        return productRepository.save(old);
    }

    @Override
    public ProductEntity getById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("error.NotFoundProduct"));
    }

    @Override
    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }
}
