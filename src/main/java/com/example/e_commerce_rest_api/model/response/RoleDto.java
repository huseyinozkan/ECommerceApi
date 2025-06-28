package com.example.e_commerce_rest_api.model.response;

import com.example.e_commerce_rest_api.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private RoleType name;
}
