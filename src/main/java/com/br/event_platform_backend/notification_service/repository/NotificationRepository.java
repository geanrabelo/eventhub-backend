package com.br.event_platform_backend.notification_service.repository;

import com.br.event_platform_backend.notification_service.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
