package com.example.e_commerce_rest_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phoneCode;
    private String phoneNumber;
    private String username;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Set<RoleDto> roles = new HashSet<>();
}
