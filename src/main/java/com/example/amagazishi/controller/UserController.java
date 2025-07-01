package com.example.amagazishi.controller;

import com.example.amagazishi.dto.UserDtoRequestRegister;
import com.example.amagazishi.exception.BaseException;
import com.example.amagazishi.mapper.UserMapper;
import com.example.amagazishi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() throws BaseException {
        return ResponseEntity.ok(userService.getAll().stream().map(UserMapper::toUserDtoResponse).toList());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewUser(@RequestBody UserDtoRequestRegister userDtoRequestRegister) throws BaseException {
        return ResponseEntity.ok(UserMapper.toUserDtoResponse(userService.save(UserMapper.toUserEntity(userDtoRequestRegister))));
    }

    @PostMapping("/get-login")
    public ResponseEntity<?> getUserByLogin(@RequestParam String username) throws BaseException {
        return ResponseEntity.ok(UserMapper.toUserDtoResponse(userService.getByUsername(username)));
    }

}
