package com.example.amagazishi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MassageSendException extends RuntimeException {
    public MassageSendException(String message) {
        super(message);
    }
}
