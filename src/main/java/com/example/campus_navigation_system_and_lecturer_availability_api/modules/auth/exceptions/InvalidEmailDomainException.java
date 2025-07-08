package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.exceptions;

public class InvalidEmailDomainException extends RuntimeException {
    public InvalidEmailDomainException(String message) {
        super(message);
    }
}
