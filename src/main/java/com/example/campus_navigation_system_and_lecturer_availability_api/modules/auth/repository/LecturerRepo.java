package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.repository;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.entity.LecturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LecturerRepo extends JpaRepository<LecturerEntity, Long> {
    Optional<LecturerEntity> findByEmail(String email);


    LecturerEntity findByName(String name);
}
