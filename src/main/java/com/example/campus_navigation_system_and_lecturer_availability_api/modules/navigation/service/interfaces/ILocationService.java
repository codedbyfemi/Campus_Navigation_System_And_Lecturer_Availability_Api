package com.example.campus_navigation_system_and_lecturer_availability_api.modules.navigation.service.interfaces;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.navigation.dto.LocationDTO;

public interface ILocationService {

    LocationDTO getLocationByLocationName(String locationName);
}
