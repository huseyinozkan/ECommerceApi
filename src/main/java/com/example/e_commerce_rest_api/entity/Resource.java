package com.example.e_commerce_rest_api.entity;

import com.example.e_commerce_rest_api.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resources")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resource extends BaseEntity {
    @Column(name = "`key`", unique = true)
    private String key;

    @Lob
    @Column(name = "value", columnDefinition = "TEXT")
    private String value;

    @ManyToOne
    @JoinColumn(name = "culture_id", nullable = false, foreignKey = @ForeignKey(name = "culture_ibfk_1"))
    private Culture culture;
}
