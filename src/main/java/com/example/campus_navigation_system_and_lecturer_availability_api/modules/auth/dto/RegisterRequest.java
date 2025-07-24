package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank
    public String name;

    @NotBlank
    public String department;

    @NotBlank
    public String officeBuilding;

    @NotBlank
    public String officeNumber;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}