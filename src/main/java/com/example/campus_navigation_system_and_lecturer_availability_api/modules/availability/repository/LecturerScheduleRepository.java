package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.repository;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.entity.LecturerSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.Optional;

public interface LecturerScheduleRepository extends JpaRepository<LecturerSchedule, Long> {

    Optional<LecturerSchedule> findByLecturerIdAndDayOfWeek(Long lecturerId, DayOfWeek dayOfWeek);

}
