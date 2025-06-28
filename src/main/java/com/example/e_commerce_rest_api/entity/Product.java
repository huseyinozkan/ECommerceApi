package com.example.e_commerce_rest_api.entity;

import com.example.e_commerce_rest_api.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Lob
    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_file_id", nullable = false)
    private File imageFile;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
