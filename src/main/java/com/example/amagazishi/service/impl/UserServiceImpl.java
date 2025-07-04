package com.example.amagazishi.service.impl;

import com.example.amagazishi.entity.UserEntity;
import com.example.amagazishi.excaption.UserNotFoundException;
import com.example.amagazishi.repository.RoleRepository;
import com.example.amagazishi.repository.UserRepository;
import com.example.amagazishi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("error.userNotFound"));
    }

    @Override
    public UserEntity getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("error.userNotFound"));
    }

    @Override
    public UserEntity save(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    public UserEntity update(UserEntity entity) {
        return null;
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

}
