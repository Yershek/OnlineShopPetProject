package com.example.amagazishi.dto;

import lombok.*;

@With
@Getter
@Setter
@Builder
public class UserDtoResponse {
    private String username;
    private Long id;
    private String email;
}
