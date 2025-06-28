package com.example.e_commerce_rest_api.repository;

import com.example.e_commerce_rest_api.entity.OtpVerification;
import com.example.e_commerce_rest_api.enums.OtpPurpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {
    void deleteAllByUserIdAndVerifiedAndPurpose(Long userId, boolean verified, OtpPurpose purpose);
    Optional<OtpVerification> findByUserIdAndCodeAndVerifiedAndPurpose(Long userId, String code, boolean verified, OtpPurpose purpose);
}
