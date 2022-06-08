package com.example.springsecurity.repository;

import com.example.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAuthRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
