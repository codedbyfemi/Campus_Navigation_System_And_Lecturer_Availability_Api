package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.service.implementation;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.JwtResponse;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.LoginRequest;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.RegisterRequest;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.UserResponse;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.entity.User;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.exceptions.InvalidEmailDomainException;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.model.Role;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.repository.UserRepository;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.service.interfaces.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncode) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncode;
    }

    @Override
    public UserResponse register(RegisterRequest request) {
        // 1. Check if user already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("Email already in use");
        }

        // 2. Create a new User object
        User user = new User();
        user.setEmail(request.getEmail());

        // 3. Hash the password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 4. Set a default role (you can customize this)
        Role role = extractRoleFromEmail(request.getEmail().toLowerCase());
        user.setRole(role);

        // 5. Save to DB
        User  savedUser = userRepository.save(user);

        // 6. Map to DTO and return
        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        return null;
    }

    private Role extractRoleFromEmail(String email) {
        if (email.endsWith("@student.babcock.edu.ng")) return Role.STUDENT;
        else if (email.endsWith("@pg.babcock.edu.ng")) return Role.PG_STUDENT;
        else if (email.endsWith("@babcock.edu.ng")) return Role.LECTURER;
        throw new InvalidEmailDomainException("Unauthorized email domain");
    }
}

