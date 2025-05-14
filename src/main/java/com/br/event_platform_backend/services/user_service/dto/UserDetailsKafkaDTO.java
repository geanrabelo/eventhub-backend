package com.br.event_platform_backend.services.user_service.dto;

import com.br.event_platform_backend.services.user_service.domain.User;
import com.br.event_platform_backend.services.user_service.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@NoArgsConstructor
@Data
public class UserDetailsKafkaDTO {

    private UUID id;
    private String fullName;
    private String email;
    private String username;
    private UserRole userRole;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserDetailsKafkaDTO(UUID id, String fullName, String email, String username, UserRole userRole, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.userRole = userRole;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserDetailsKafkaDTO(User user){
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.userRole = user.getUserRole();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
