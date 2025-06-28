package com.example.e_commerce_rest_api.model.response;

import com.example.e_commerce_rest_api.model.response.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto extends BaseDto {
    private String name;
    private String city;
    private String district;
    private String addressDescription;
}
