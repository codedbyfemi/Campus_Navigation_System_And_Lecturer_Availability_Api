package com.example.campus_navigation_system_and_lecturer_availability_api.modules.navigation.service.implementation;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.navigation.dto.LocationDTO;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.navigation.entity.LocationEntity;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.navigation.repository.LocationRepository;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.navigation.service.interfaces.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements ILocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Override
    public LocationDTO getLocationByLocationName(String locationName) {
        LocationEntity location = locationRepository.getByLocationName(locationName);

        return toDTO(location);
    }

    private LocationDTO toDTO(LocationEntity entity) {
        LocationDTO dto = new LocationDTO();
        dto.setLocationName(entity.getLocationName());
        dto.setLocationDescription(entity.getLocationDescription());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        return dto;
    }
}
