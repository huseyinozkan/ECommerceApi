package com.example.e_commerce_rest_api.utils.exception.exceptions;

public class RateLimitException extends RuntimeException {
    public RateLimitException(String message) {
        super(message);
    }
}