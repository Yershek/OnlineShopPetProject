package com.example.amagazishi.service;

import com.example.amagazishi.entity.UserEntity;

public interface UserService extends BaseService<UserEntity> {
    UserEntity getByUsername(String username);
}
