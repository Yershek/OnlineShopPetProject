package com.example.amagazishi.mapper;

import com.example.amagazishi.dto.UserDtoRequestRegister;
import com.example.amagazishi.dto.UserDtoResponse;
import com.example.amagazishi.entity.UserEntity;

public class UserMapper {
    public static UserEntity toUserEntity(UserDtoRequestRegister userDtoRequestRegister) {
        return UserEntity
                .builder()
                .username(userDtoRequestRegister.getUsername())
                .password(userDtoRequestRegister.getPassword())
                .email(userDtoRequestRegister.getEmail())
                .build();
    }

    public static UserDtoResponse toUserDtoResponse(UserEntity userEntity) {
        return UserDtoResponse
                .builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .build();
    }

}
