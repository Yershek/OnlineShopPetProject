package com.example.amagazishi.service;

import com.example.amagazishi.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    UserEntity getCurrentUser();
    String login(String username, String password);
    String logout();
    void passwordRestoration(String emailOrLogin);
    String updatePassword(String activeCode, String newPassword);
}
