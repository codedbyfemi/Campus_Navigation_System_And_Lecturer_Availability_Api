package com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.enums;

/**
 * Represents the current override status of a lecturer's availability
 * for a specific day. When set to DEFAULT, the system uses the schedule.
 */
public enum AvailabilityStatus {

    /**
     * Force the lecturer to be marked available,
     * regardless of the time slots.
     */
    AVAILABLE,

    /**
     * Force the lecturer to be marked unavailable,
     * even if the time slots say otherwise.
     */
    UNAVAILABLE,

    /**
     * Use the time slots as defined in the schedule.
     */
    DEFAULT
}
