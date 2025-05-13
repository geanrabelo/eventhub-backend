package com.br.event_platform_backend.services.user_service.dto;

import com.br.event_platform_backend.services.user_service.enums.UserRole;

public record RegisterDTO(String fullName,
                          String email,
                          String username,
                          String password,
                          UserRole userRole) {
}
