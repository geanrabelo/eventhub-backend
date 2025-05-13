package com.br.event_platform_backend.services.user_service.repository;

import com.br.event_platform_backend.services.user_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    UserDetails findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
