package com.example.e_commerce_rest_api.service;


import com.example.e_commerce_rest_api.entity.User;

public interface IUserService {
    User getUser();
    User findByUsername(String username);
}
