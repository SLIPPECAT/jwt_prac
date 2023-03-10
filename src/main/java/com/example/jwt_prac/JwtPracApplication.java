package com.example.jwt_prac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JwtPracApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtPracApplication.class, args);
    }

}
