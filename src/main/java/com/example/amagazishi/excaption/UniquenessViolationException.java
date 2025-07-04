package com.example.amagazishi.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UniquenessViolationException extends BaseException {
    public UniquenessViolationException(String message) {
        super(message);
    }
}
