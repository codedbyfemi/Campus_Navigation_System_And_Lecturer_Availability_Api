package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.controllers;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.*;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.entity.LecturerEntity;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.entity.User;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.repository.LecturerRepo;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.service.implementation.UserService;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.util.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final UserService userService;
    private final LecturerRepo lecturerRepo;

    public AuthController(UserService userService, LecturerRepo lecturerRepo) {
        this.userService = userService;
        this.lecturerRepo = lecturerRepo;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        UserResponse registeredUser = userService.register(request);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        JwtResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/details")
    @PreAuthorize("hasRole('LECTURER')")
    public ResponseEntity<LecturerDetailsDTO> details(@RequestBody LecturerDetailsDTO details, Authentication authentication) {
        LecturerDetailsDTO deets = userService.setDetails(details, authentication);
        return ResponseEntity.ok(deets);
    }

    @GetMapping("/auth/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        LecturerEntity lect = lecturerRepo.findByEmail(user.getEmail()).orElse(null);


        return ResponseEntity.ok(new JwtResponse(
                null,
                null,
                lect.getName(),
                user.getEmail(),
                user.getRole()
        ));
    }

}