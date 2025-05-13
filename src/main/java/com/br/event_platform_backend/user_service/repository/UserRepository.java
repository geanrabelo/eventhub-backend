package com.br.event_platform_backend.user_service.repository;

import com.br.event_platform_backend.user_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
