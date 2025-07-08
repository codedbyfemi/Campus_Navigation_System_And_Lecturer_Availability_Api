package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private Role role;
}