package com.example.e_commerce_rest_api.repository;

import com.example.e_commerce_rest_api.entity.Agreement;
import com.example.e_commerce_rest_api.enums.AgreementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    Optional<Agreement> findTopByTypeAndCultureNameOrderByVersionDesc(AgreementType type, String cultureName);
}