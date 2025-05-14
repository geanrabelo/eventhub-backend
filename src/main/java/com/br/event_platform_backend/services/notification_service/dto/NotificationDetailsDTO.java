package com.br.event_platform_backend.services.notification_service.dto;

import com.br.event_platform_backend.services.notification_service.domain.Notification;
import com.br.event_platform_backend.services.notification_service.enums.NotificationStatus;
import com.br.event_platform_backend.services.notification_service.enums.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationDetailsDTO(UUID id,
                                     UUID userId,
                                     NotificationType notificationType,
                                     String subject,
                                     String message,
                                     NotificationStatus notificationStatus,
                                     LocalDateTime sentAt) {

    public NotificationDetailsDTO(Notification notification){
        this(
                notification.getId(),
                notification.getUser().getId(),
                notification.getNotificationType(),
                notification.getSubject(),
                notification.getMessage(),
                notification.getNotificationStatus(),
                notification.getSentAt()
        );
    }
}
