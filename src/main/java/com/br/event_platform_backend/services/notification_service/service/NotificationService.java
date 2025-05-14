package com.br.event_platform_backend.services.notification_service.service;

import com.br.event_platform_backend.services.notification_service.dto.NotificationDetailsDTO;

import java.util.List;
import java.util.UUID;

public interface NotificationService {

    void sendMessage();

    List<NotificationDetailsDTO> findAll();

    NotificationDetailsDTO findById(UUID id);
}
