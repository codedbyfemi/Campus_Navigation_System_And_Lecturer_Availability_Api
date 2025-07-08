package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.repository;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}