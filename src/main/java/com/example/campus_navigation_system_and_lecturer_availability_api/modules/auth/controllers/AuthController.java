package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.controllers;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.JwtResponse;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.LoginRequest;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.RegisterRequest;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.UserResponse;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.entity.User;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.service.implementation.UserService;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.util.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        UserResponse registeredUser = userService.register(request);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        JwtResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        return ResponseEntity.ok(new JwtResponse(
                null,
                null,
                user.getEmail(),
                user.getRole()
        ));
    }

}