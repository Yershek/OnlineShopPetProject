package com.example.amagazishi.service;

import com.example.amagazishi.entity.BaseEntity;

import java.util.List;

public interface BaseService <T extends BaseEntity> {
    T save(T entity);
    T update(T entity);
    T getById(Long id);
    List<T> getAll();
}
