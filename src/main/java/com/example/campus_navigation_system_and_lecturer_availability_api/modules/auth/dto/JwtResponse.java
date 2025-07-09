package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.model.Role;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.util.DateUtil;
import lombok.Data;

import java.util.Date;

@Data
public class JwtResponse {
    private final String token;
    private final Date expiration;
    private final String readableExpiry;
    private final String email;
    private final Role role;

    public JwtResponse(String token, Date expiration, String email, Role role) {
        this.token = token;
        this.expiration = expiration;
        this.readableExpiry = DateUtil.formatDate(expiration); // Format after assignment
        this.email = email;
        this.role = role;
    }
}