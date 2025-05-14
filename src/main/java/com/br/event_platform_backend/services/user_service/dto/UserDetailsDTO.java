package com.br.event_platform_backend.services.user_service.dto;

import com.br.event_platform_backend.services.user_service.domain.User;
import com.br.event_platform_backend.services.user_service.enums.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDetailsDTO(UUID id,
                             String fullName,
                             String email,
                             String username,
                             UserRole userRole,
                             LocalDateTime createdAt,
                             LocalDateTime updatedAt) {
    public UserDetailsDTO(User user){
        this(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getUsername(),
                user.getUserRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
