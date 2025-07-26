package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.service.interfaces;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.dto.*;

public interface LecturerScheduleService {

    LecturerScheduleResponse createSchedule(CreateScheduleRequest request);

    LecturerScheduleResponse2 getScheduleByLecturerAndDay(String lecturerName, String upperCase);

    LecturerAvailabilityStatus isLecturerAvailableNow(String lecturerId);
}
