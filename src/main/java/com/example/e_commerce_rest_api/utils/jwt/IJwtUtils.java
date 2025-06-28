package com.example.e_commerce_rest_api.utils.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtUtils {
    String generateToken(UserDetails userDetails);
    String getUsernameByToken(String token);
    boolean isTokenExpired(String token);
    Object getClaimsByKey(String token, String key);
}

