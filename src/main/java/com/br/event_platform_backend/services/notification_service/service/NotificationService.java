package com.br.event_platform_backend.services.notification_service.service;

import com.br.event_platform_backend.services.notification_service.dto.NotificationDetailsDTO;
import com.br.event_platform_backend.services.notification_service.dto.NotificationSendDTO;
import jakarta.mail.MessagingException;

import java.util.List;
import java.util.UUID;

public interface NotificationService {

    void sendMessage(NotificationSendDTO notificationSendDTO) throws MessagingException;

    List<NotificationDetailsDTO> findAll();

    NotificationDetailsDTO findById(UUID id);
}
