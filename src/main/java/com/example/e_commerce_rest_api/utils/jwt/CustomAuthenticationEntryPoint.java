package com.example.e_commerce_rest_api.utils.jwt;

import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.model.response.base.BaseResponse;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final MessageSourceHelper messageSourceHelper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        BaseResponse<Object> baseResponse = BaseResponse.error()
                .addMessage(messageSourceHelper.getMessage(MessageKey.UNAUTHORIZED_MESSAGE));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(objectMapper.writeValueAsString(baseResponse));
    }
}