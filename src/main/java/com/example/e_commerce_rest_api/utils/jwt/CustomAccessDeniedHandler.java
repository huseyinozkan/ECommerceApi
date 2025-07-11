package com.example.e_commerce_rest_api.utils.jwt;

import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.dto.response.base.BaseResponse;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final MessageSourceHelper messageSourceHelper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        BaseResponse<Object> baseResponse = BaseResponse.error()
                .addMessage(messageSourceHelper.getMessage(MessageKey.FORBIDDEN_MESSAGE));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(objectMapper.writeValueAsString(baseResponse));
    }
}