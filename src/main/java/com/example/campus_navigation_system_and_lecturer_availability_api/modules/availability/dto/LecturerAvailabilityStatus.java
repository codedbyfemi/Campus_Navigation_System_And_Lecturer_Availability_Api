package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LecturerAvailabilityStatus {
    private boolean available;
    private LocalTime currentTime;
    private SlotResponse currentSlot;
    private String message;
    private LocalTime nextAvailableAt;
    private LocalTime availableUntil;

    public LecturerAvailabilityStatus(boolean available, LocalTime currentTime, SlotResponse currentSlot, String message) {
        this.available = available;
        this.currentTime = currentTime;
        this.currentSlot = currentSlot;
        this.message = message;
    }

    public LecturerAvailabilityStatus(boolean available, LocalTime currentTime, SlotResponse currentSlot, String message, LocalTime availableUntil) {
        this.available = available;
        this.currentTime = currentTime;
        this.currentSlot = currentSlot;
        this.message = message;
        this.availableUntil = availableUntil;
    }

    public LecturerAvailabilityStatus(boolean available, LocalTime currentTime, LocalTime nextAvailableAt, String message) {
        this.available = available;
        this.currentTime = currentTime;
        this.nextAvailableAt = nextAvailableAt;
        this.message = message;
    }


}
