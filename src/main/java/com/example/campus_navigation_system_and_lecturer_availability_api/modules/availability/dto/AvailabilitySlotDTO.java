package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.dto;

import lombok.Data;

import java.time.LocalTime;

/**
 * Represents an individual time-based availability slot.
 */
@Data
public class AvailabilitySlotDTO {

    private LocalTime startTime;
    private LocalTime endTime;
    private String location;

    // Getters and setters
}
