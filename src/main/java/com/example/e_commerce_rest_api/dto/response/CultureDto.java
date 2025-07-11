package com.example.e_commerce_rest_api.dto.response;

import com.example.e_commerce_rest_api.dto.response.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CultureDto extends BaseDto {
    private String name;
    private String title;
}
