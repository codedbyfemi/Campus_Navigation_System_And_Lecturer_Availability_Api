package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.controllers;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.dto.*;
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
     * @param request is the request body
     * @return is the return value
     */
    @PostMapping
    @PreAuthorize("hasRole('LECTURER')")
    public ResponseEntity<LecturerScheduleResponse> createSchedule(
            @Valid @RequestBody CreateScheduleRequest request
    ) {
        LecturerScheduleResponse response = scheduleService.createSchedule(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint for retrieving schedule by day
     * @param lecturerId as the query parameter
     * @param day as the query parameter
     * @return return value
     */
    @GetMapping("/{lecturerId}/{day}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('PG_STUDENT') or hasRole('LECTURER')")
    public ResponseEntity<LecturerScheduleResponse2> getScheduleByDay(
            @PathVariable Long lecturerId,
            @PathVariable String day
    ) {
        LecturerScheduleResponse2 response = scheduleService.getScheduleByLecturerAndDay(lecturerId, day.toUpperCase());
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint for retrieving current availability status
     * @param lecturerId as the query parameter
     * @return return value
     */
    @GetMapping("/check/{lecturerId}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('PG_STUDENT') or hasRole('LECTURER')")
    public ResponseEntity<LecturerAvailabilityStatus> isLecturerAvailableNow(@PathVariable Long lecturerId) {
        LecturerAvailabilityStatus response = scheduleService.isLecturerAvailableNow(lecturerId);
        return ResponseEntity.ok(response);
    }



}
