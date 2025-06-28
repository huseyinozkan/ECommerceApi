package com.example.e_commerce_rest_api.service.impl;

import com.example.e_commerce_rest_api.entity.User;
import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.repository.UserRepository;
import com.example.e_commerce_rest_api.service.IUserService;
import com.example.e_commerce_rest_api.utils.exception.exceptions.NotFoundException;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final MessageSourceHelper messageSourceHelper;

    @Override
    public User getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails userDetails)) throw new NotFoundException(messageSourceHelper.getMessage(MessageKey.USER_NOT_FOUND));
        return findByUsername(userDetails.getUsername());
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.USER_NOT_FOUND)));
    }
}
