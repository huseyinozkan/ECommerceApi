package com.example.e_commerce_rest_api.controller.base;

import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.dto.response.base.BaseResponse;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class BaseController {
    private final MessageSourceHelper messageSourceHelper;

    public <T> ResponseEntity<BaseResponse<T>> ok(T result) {
        String message = messageSourceHelper.getMessage(MessageKey.OPERATION_SUCCESSFUL);
        BaseResponse<T> response = BaseResponse.success(result).addMessage(message);
        return ResponseEntity.ok(response);
    }
}
