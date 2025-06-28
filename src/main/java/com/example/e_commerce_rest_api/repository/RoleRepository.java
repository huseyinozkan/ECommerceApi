package com.example.e_commerce_rest_api.repository;

import com.example.e_commerce_rest_api.entity.Role;
import com.example.e_commerce_rest_api.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleType name);

    boolean existsByName(RoleType name);
}
