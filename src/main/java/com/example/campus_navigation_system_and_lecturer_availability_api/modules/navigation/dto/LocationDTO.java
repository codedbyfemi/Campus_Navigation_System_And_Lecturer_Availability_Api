package com.example.campus_navigation_system_and_lecturer_availability_api.modules.navigation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    private String locationName;

    private String locationDescription;

    private float latitude;

    private float longitude;
}
