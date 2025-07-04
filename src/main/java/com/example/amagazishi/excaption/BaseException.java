package com.example.amagazishi.excaption;

public abstract class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }
}
