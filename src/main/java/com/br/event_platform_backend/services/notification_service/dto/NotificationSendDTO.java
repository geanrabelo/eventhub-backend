package com.br.event_platform_backend.services.notification_service.dto;

import com.br.event_platform_backend.services.notification_service.enums.NotificationStatus;
import com.br.event_platform_backend.services.notification_service.enums.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationSendDTO(UUID userId,
                                  String subject,
                                  String message) {

}
