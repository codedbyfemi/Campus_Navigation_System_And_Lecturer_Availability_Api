package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.repository;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.entity.LecturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepo extends JpaRepository<LecturerEntity, Long> {
}
