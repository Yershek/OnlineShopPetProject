package com.example.amagazishi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeacherNotFoundException extends BaseException {
    public TeacherNotFoundException(String message) {
        super(message);
    }
}
