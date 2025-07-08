package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.service.interfaces;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.JwtResponse;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.LoginRequest;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.RegisterRequest;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.UserResponse;

public interface IUserService {
    UserResponse register(RegisterRequest request);
    JwtResponse login(LoginRequest request);

}
