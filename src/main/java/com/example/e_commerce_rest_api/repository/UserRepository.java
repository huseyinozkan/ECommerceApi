package com.example.e_commerce_rest_api.repository;

import com.example.e_commerce_rest_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByPhoneCodeAndPhoneNumber(String phoneCode, String phoneNumber);

    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.userAgreement.id = :agreementId WHERE u.id = :userId")
    void updateUserAgreementById(@Param("userId") Long userId, @Param("agreementId") Long agreementId);


    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.privacyAgreement.id = :agreementId WHERE u.id = :userId")
    void updatePrivacyAgreementById(@Param("userId") Long userId, @Param("agreementId") Long agreementId);

}