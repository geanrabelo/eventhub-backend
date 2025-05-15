package com.br.event_platform_backend.services.notification_service.controller;

import com.br.event_platform_backend.services.auth_service.dto.MessageDTO;
import com.br.event_platform_backend.services.notification_service.dto.NotificationDetailsDTO;
import com.br.event_platform_backend.services.notification_service.dto.NotificationSendDTO;
import com.br.event_platform_backend.services.notification_service.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/platform/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MessageDTO> sendNotification(@RequestBody @Validated NotificationSendDTO notificationSendDTO){
        notificationService.sendMessage(notificationSendDTO);
        return ResponseEntity.ok(new MessageDTO("Notification send and registered successfully"));
    }

    @GetMapping
    public ResponseEntity<List<NotificationDetailsDTO>> findAll(){
        return ResponseEntity.ok(notificationService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<NotificationDetailsDTO> findById(String id){
        return ResponseEntity.ok(notificationService.findById(UUID.fromString(id)));
    }
}
