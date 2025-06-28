package com.example.e_commerce_rest_api.repository;

import com.example.e_commerce_rest_api.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findByCultureId(Long cultureId);
    Optional<Resource> findByCultureIdAndKey(Long cultureId, String key);
}
