package com.example.amagazishi.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDtoResponse {
    private String username;
    private Long id;
    private String email;

    public UserDtoResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public UserDtoResponse setEmail(String email) {
        this.email = email;
        return this;
    }
}
