package com.example.e_commerce_rest_api.entity;

import com.example.e_commerce_rest_api.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cultures")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Culture extends BaseEntity {
    @Column(name = "name", unique = true, nullable = false, length = 20)
    private String name;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @OneToMany(mappedBy = "culture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resource> resources;
}
