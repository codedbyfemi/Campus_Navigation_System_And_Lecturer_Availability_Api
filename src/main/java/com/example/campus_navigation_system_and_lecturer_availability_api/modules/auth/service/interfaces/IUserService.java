package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.service.interfaces;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.*;
import org.springframework.security.core.Authentication;

public interface IUserService {
    UserResponse register(RegisterRequest request);
    JwtResponse login(LoginRequest request);
    LecturerDetailsDTO setDetails(LecturerDetailsDTO dto, Authentication authentication);

}
