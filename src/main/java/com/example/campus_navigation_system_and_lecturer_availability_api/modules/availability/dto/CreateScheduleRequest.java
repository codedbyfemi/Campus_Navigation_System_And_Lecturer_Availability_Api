package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.dto;

import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;

/**
 * Request DTO to create a schedule for a lecturer on a specific day,
 * with one or more time slots.
 */
@Data
public class CreateScheduleRequest {

    private Long lecturerId;
    private DayOfWeek dayOfWeek;
    private List<AvailabilitySlotDTO> slots;

    // Getters and setters
}
