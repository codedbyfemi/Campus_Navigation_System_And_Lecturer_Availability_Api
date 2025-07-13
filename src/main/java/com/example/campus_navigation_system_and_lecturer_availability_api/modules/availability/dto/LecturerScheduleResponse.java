package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.dto;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.enums.AvailabilityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Response DTO representing a lecturer's schedule for a specific day,
 * including slots and override status.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LecturerScheduleResponse {

    private Long id;
    private Long lecturerId;
    private DayOfWeek dayOfWeek;
    private AvailabilityStatus statusOverride;
    private LocalDateTime lastModified;
    private List<AvailabilitySlotDTO> slots;

    // Getters and setters
}
