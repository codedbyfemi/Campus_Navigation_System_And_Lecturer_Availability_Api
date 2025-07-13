package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

/**
 * Represents a single availability window (e.g., 9:00 to 11:00 AM)
 * for a lecturer on a specific day of the week.
 */
@Entity
@Data
@Table(name = "availability_slot", schema = "availability")
public class AvailabilitySlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Start time of this availability slot (e.g., 09:00).
     */
    private LocalTime startTime;

    /**
     * End time of this availability slot (e.g., 11:00).
     */
    private LocalTime endTime;

    /**
     * Location where the lecturer is available during this slot.
     */
    private String location;

    /**
     * The parent schedule this slot belongs to (e.g., Dr. A's Monday).
     */
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private LecturerSchedule schedule;

    // Constructors, getters, setters
}
