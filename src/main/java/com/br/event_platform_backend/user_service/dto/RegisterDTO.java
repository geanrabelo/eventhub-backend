package com.br.event_platform_backend.user_service.dto;

import com.br.event_platform_backend.user_service.enums.UserRole;

public record RegisterDTO(String fullName,
                          String email,
                          String password,
                          UserRole userRole) {
}
