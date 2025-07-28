package com.example.campus_navigation_system_and_lecturer_availability_api.modules.navigation.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "location_entity", schema = "locations")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String locationName;

    private String locationDescription;

    private float latitude;

    private float longitude;
}
