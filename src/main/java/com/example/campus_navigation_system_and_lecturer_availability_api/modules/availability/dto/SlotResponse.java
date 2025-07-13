package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotResponse {
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;

    // Constructor, Getters, Setters
}
