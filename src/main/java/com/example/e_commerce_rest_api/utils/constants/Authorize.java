package com.example.e_commerce_rest_api.utils.constants;

public class Authorize {
    public static final String USER = "hasRole('USER')";
    public static final String ADMIN = "hasRole('ADMIN')";
    public static final String USER_ADMIN = "hasAnyRole('USER', 'ADMIN')";
}