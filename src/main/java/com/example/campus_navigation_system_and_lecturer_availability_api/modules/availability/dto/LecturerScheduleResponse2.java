package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LecturerScheduleResponse2 {
    private String lecturername;
    private DayOfWeek dayOfWeek;
    private List<SlotResponse> slots;
}
