package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class JwtTokenData {
    private final String token;
    private final Date expiration;
}
