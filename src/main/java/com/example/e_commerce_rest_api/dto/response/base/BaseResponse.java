package com.example.e_commerce_rest_api.dto.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(value = JsonInclude.Include.ALWAYS)
@Getter
@Setter
public class BaseResponse<T>{
    T data;
    Boolean success;
    List<String> messages;

    public BaseResponse(T data, boolean success, List<String> messages) {
        this.data = data;
        this.success = success;
        this.messages = messages;
    }

    public BaseResponse<T> addMessage(String message) {
        this.messages.add(message);
        return this;
    }

    public BaseResponse<T> addMessages(List<String> messages) {
        this.messages.addAll(messages);
        return this;
    }

    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<T>(data, true, new ArrayList<>());
    }

    public static <T> BaseResponse<T> error(){
        return new BaseResponse<T>(null, false, new ArrayList<>());
    }
}
