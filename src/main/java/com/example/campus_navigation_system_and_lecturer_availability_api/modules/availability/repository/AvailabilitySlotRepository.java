package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.repository;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.entity.AvailabilitySlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilitySlotRepository extends JpaRepository<AvailabilitySlot, Long> {
}
