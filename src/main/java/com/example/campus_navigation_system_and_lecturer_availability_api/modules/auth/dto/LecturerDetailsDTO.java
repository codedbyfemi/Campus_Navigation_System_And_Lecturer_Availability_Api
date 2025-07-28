package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.entity.LecturerSchedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturerDetailsDTO {

    public String name;

    public String department;

    public String officeBuilding;

    public String officeNumber;

    public String email;

    public List<LecturerSchedule> schedules;

}
