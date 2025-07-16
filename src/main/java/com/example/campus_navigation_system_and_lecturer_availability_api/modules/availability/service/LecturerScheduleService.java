package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.service;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.dto.*;

public interface LecturerScheduleService {

    LecturerScheduleResponse createSchedule(CreateScheduleRequest request);

    LecturerScheduleResponse2 getScheduleByLecturerAndDay(Long lecturerId, String upperCase);

    LecturerAvailabilityStatus isLecturerAvailableNow(Long lecturerId);
}
