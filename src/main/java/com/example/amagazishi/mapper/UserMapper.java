package com.example.amagazishi.mapper;

import com.example.amagazishi.dto.UserDtoRequestRegister;
import com.example.amagazishi.dto.UserDtoResponse;
import com.example.amagazishi.entity.UserEntity;

public class UserMapper {
    public static UserEntity toUserEntity(UserDtoRequestRegister userDtoRequestRegister) {
        return new UserEntity()
                .setUsername(userDtoRequestRegister.getUsername())
                .setPassword(userDtoRequestRegister.getPassword())
                .setEmail(userDtoRequestRegister.getEmail());
    }

    public static UserDtoResponse toUserDtoResponse(UserEntity userEntity) {
        return new UserDtoResponse()
                .setId(userEntity.getId())
                .setUsername(userEntity.getUsername())
                .setEmail(userEntity.getEmail());
    }

}
