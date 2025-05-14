package com.br.event_platform_backend.services.notification_service.service.impl;

import com.br.event_platform_backend.services.notification_service.dto.NotificationDetailsDTO;
import com.br.event_platform_backend.services.notification_service.repository.NotificationRepository;
import com.br.event_platform_backend.services.notification_service.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void sendMessage() {

    }

    @Override
    public List<NotificationDetailsDTO> findAll() {
        return List.of();
    }

    @Override
    public NotificationDetailsDTO findById(UUID id) {
        return null;
    }
}
