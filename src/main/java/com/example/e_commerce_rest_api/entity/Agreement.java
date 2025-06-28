package com.example.e_commerce_rest_api.entity;

import com.example.e_commerce_rest_api.entity.base.BaseEntity;
import com.example.e_commerce_rest_api.enums.AgreementType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "agreements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agreement extends BaseEntity {
    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "cultureName", nullable = false, length = 20)
    private String cultureName;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private AgreementType type;
}
