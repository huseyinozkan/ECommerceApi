package com.example.e_commerce_rest_api.repository;

import com.example.e_commerce_rest_api.entity.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CultureRepository extends JpaRepository<Culture, Long> {
    boolean existsByName(String name);
    Optional<Culture> findByName(String name);
}
