package com.example.amagazishi.service.impl;

import com.example.amagazishi.entity.BasketEntity;
import com.example.amagazishi.entity.UserEntity;
import com.example.amagazishi.exception.UserNotFoundException;
import com.example.amagazishi.repository.BasketRepository;
import com.example.amagazishi.repository.RoleRepository;
import com.example.amagazishi.repository.UserRepository;
import com.example.amagazishi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BasketRepository basketRepository;
    private final PasswordEncoder passwordEncoder;

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
        BasketEntity basket = BasketEntity.builder().build();
        basketRepository.save(basket);
        entity.setBasket(basket);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    @Override
    public UserEntity update(UserEntity entity) {
        UserEntity user = userRepository.findById(entity.getId()).orElseThrow(() -> new UserNotFoundException("error.userNotFound"));
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setEmail(entity.getEmail());
        user.setRoles(entity.getRoles());
        if(entity.getBasket() != null) {
            user.setBasket(entity.getBasket());
        }
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

}
