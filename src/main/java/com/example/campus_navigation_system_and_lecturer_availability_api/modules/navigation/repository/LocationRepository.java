package com.example.campus_navigation_system_and_lecturer_availability_api.modules.navigation.repository;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.navigation.dto.LocationDTO;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.navigation.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationDTO, Long> {
    LocationEntity finadByLocationName(String locationName);
}
