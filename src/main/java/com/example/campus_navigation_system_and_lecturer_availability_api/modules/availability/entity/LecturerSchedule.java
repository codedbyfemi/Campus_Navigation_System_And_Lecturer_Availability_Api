package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.entity;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.entity.LecturerEntity;
import jakarta.persistence.*;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.enums.AvailabilityStatus;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a lecturer's availability schedule for a specific day of the week.
 * Each schedule entry includes the day, an optional override status,
 * and a list of time-based availability slots.
 */
@Entity
@Data
@Table(name = "lecturer_schedule", schema = "availability")
public class LecturerSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * The day of the week this schedule is for (e.g. MONDAY).
     */
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    /**
     * A manual override for real-time status, e.g. UNAVAILABLE due to emergency.
     * DEFAULT means fallback to time slots.
     */
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus statusOverride = AvailabilityStatus.DEFAULT;

    /**
     * Timestamp for the last time override status was changed.
     */
    private LocalDateTime lastModified;

    // Slots will be linked here in the next step
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilitySlot> slots;

    /**
     * The ID of the lecturer (references the User entity).
     */
    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private LecturerEntity lecturer;

}
