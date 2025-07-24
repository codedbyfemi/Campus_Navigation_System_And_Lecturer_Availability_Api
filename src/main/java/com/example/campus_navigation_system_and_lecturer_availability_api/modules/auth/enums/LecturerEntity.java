package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.enums;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.availability.entity.LecturerSchedule;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "lecturer_entity", schema = "profiles")
public class LecturerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String department;

    private String email;

    private String officeBuilding;

    private String officeNumber;

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<LecturerSchedule> schedule;
}
