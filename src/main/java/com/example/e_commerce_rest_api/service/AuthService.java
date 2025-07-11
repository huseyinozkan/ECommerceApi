package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.dto.request.*;
import com.example.e_commerce_rest_api.dto.response.LoginResponse;
import com.example.e_commerce_rest_api.dto.response.UserDto;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    LoginResponse refreshToken(RefreshTokenRequest request);
    UserDto register(RegisterRequest request);
    void sendOtpCode(SendOtpCodeRequest request);
    void forgotPassword(ForgotPasswordRequest request);
    void updatePassword(UpdatePasswordRequest request);
    void approveAgreements(ApproveAgreementRequest request);
}

