package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.controllers;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.dto.CreateScheduleRequest;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.dto.LecturerAvailabilityStatus;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.dto.LecturerScheduleResponse;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.dto.LecturerScheduleResponse2;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.service.LecturerScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/availability/schedule")
public class LecturerScheduleController {

    @Autowired
    private LecturerScheduleService scheduleService;

    /**
     * Endpoint for creating a schedule with one or more availability slots
     */
    @PostMapping
    @PreAuthorize("hasRole('LECTURER')")
    public ResponseEntity<LecturerScheduleResponse> createSchedule(
            @Valid @RequestBody CreateScheduleRequest request
    ) {
        LecturerScheduleResponse response = scheduleService.createSchedule(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{lecturerId}/{day}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('PG_STUDENT') or hasRole('LECTURER')")
    public ResponseEntity<LecturerScheduleResponse2> getScheduleByDay(
            @PathVariable Long lecturerId,
            @PathVariable String day
    ) {
        LecturerScheduleResponse2 response = scheduleService.getScheduleByLecturerAndDay(lecturerId, day.toUpperCase());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check/{lecturerId}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('PG_STUDENT') or hasRole('LECTURER')")
    public ResponseEntity<LecturerAvailabilityStatus> isLecturerAvailableNow(@PathVariable Long lecturerId) {
        LecturerAvailabilityStatus response = scheduleService.isLecturerAvailableNow(lecturerId);
        return ResponseEntity.ok(response);
    }



}
