package com.example.e_commerce_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan("com.example.e_commerce_rest_api")
@EntityScan("com.example.e_commerce_rest_api")
@EnableJpaRepositories("com.example.e_commerce_rest_api")
public class ECommerceRestApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ECommerceRestApiApplication.class, args);
	}
}
