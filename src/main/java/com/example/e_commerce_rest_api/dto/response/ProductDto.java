package com.example.e_commerce_rest_api.dto.response;

import com.example.e_commerce_rest_api.dto.response.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends BaseDto {
    private String name;
    private String description;
    private BigDecimal price;
    private FileIdDto imageFile;
    private CategoryDto category;
}
