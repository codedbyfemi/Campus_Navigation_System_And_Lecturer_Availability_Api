package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.service;

import com.example.campus_navigation_system_and_lecturer_availability_api.common.exception.ResourceNotFoundException;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.dto.*;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.entity.*;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.enums.AvailabilityStatus;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LecturerScheduleServiceImpl implements LecturerScheduleService {

    @Autowired
    private LecturerScheduleRepository scheduleRepository;

    @Override
    public LecturerScheduleResponse createSchedule(CreateScheduleRequest request) {
        // Build the schedule
        LecturerSchedule schedule = new LecturerSchedule();
        schedule.setId(request.getLecturerId());
        schedule.setDayOfWeek(request.getDayOfWeek());
        schedule.setStatusOverride(AvailabilityStatus.DEFAULT); // by default, follow slot logic
        schedule.setLastModified(LocalDateTime.now());

        // Convert slot DTOs into entities
        List<AvailabilitySlot> slots = request.getSlots().stream().map(dto -> {
            AvailabilitySlot slot = new AvailabilitySlot();
            slot.setStartTime(dto.getStartTime());
            slot.setEndTime(dto.getEndTime());
            slot.setLocation(dto.getLocation());
            slot.setSchedule(schedule); // set back-reference
            return slot;
        }).collect(Collectors.toList());

        // Assign slots to schedule
        schedule.setSlots(slots);

        // Save schedule (cascades to slots)
        LecturerSchedule saved = scheduleRepository.save(schedule);

        // Build response
        LecturerScheduleResponse response = new LecturerScheduleResponse();
        response.setId(saved.getId());
        response.setLecturerId(saved.getLecturer().getId());
        response.setDayOfWeek(saved.getDayOfWeek());
        response.setStatusOverride(saved.getStatusOverride());
        response.setLastModified(saved.getLastModified());

        List<AvailabilitySlotDTO> responseSlots = saved.getSlots().stream().map(slot -> {
            AvailabilitySlotDTO dto = new AvailabilitySlotDTO();
            dto.setStartTime(slot.getStartTime());
            dto.setEndTime(slot.getEndTime());
            dto.setLocation(slot.getLocation());
            return dto;
        }).collect(Collectors.toList());

        response.setSlots(responseSlots);

        return response;
    }

    /**
     * TODO: Implement schedule override
     * @param lecturerId
     * @param day
     * @return
     */
    @Override
    public LecturerScheduleResponse2 getScheduleByLecturerAndDay(Long lecturerId, String day) {
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day); // MONDAY, TUESDAY, etc.

        LecturerSchedule schedule = scheduleRepository
                .findByLecturerIdAndDayOfWeek(lecturerId, dayOfWeek)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));

        // Map to DTO
        List<SlotResponse> slotResponses = schedule.getSlots()
                .stream()
                .map(slot -> new SlotResponse(
                        slot.getStartTime(),
                        slot.getEndTime(),
                        slot.getLocation()
                ))
                .collect(Collectors.toList());


        return new LecturerScheduleResponse2(schedule.getLecturer().getId(), dayOfWeek, slotResponses);
    }

    @Override
    public LecturerAvailabilityStatus isLecturerAvailableNow(Long lecturerId) {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        LocalTime now = LocalTime.now();
        LocalTime availableUntil;
        LocalTime nextAvailableAt = null;
        boolean available =  false;

        LecturerSchedule schedule = scheduleRepository
                .findByLecturerIdAndDayOfWeek(lecturerId, today)
                .orElse(null);

        if (schedule == null || schedule.getSlots().isEmpty()) {
            return new LecturerAvailabilityStatus(false, now, (SlotResponse) null, "No schedule found for today.");
        }

        for (AvailabilitySlot slot : schedule.getSlots()) {
            if (!slot.getStartTime().isAfter(now) && !slot.getEndTime().isBefore(now)) {
                SlotResponse currentSlot = new SlotResponse(slot.getStartTime(), slot.getEndTime(), slot.getLocation());
                availableUntil = slot.getEndTime();
                return new LecturerAvailabilityStatus(true, now, currentSlot, "Lecturer is available now.", availableUntil);
            }else if (now.isBefore(slot.getStartTime()) && nextAvailableAt == null) {
                nextAvailableAt = slot.getStartTime();
            }
        }

        return new LecturerAvailabilityStatus(available, now, nextAvailableAt, "Lecturer is unavailable now.");

    }



}

