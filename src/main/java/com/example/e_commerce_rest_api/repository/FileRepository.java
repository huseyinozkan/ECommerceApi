package com.example.e_commerce_rest_api.repository;

import com.example.e_commerce_rest_api.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
