package com.br.event_platform_backend.services.notification_service.service.impl;

import com.br.event_platform_backend.exceptions.NotificationNotFound;
import com.br.event_platform_backend.exceptions.UserNotFound;
import com.br.event_platform_backend.services.notification_service.domain.Notification;
import com.br.event_platform_backend.services.notification_service.dto.NotificationDetailsDTO;
import com.br.event_platform_backend.services.notification_service.dto.NotificationSendDTO;
import com.br.event_platform_backend.services.notification_service.enums.NotificationStatus;
import com.br.event_platform_backend.services.notification_service.enums.NotificationType;
import com.br.event_platform_backend.services.notification_service.repository.NotificationRepository;
import com.br.event_platform_backend.services.notification_service.service.NotificationService;
import com.br.event_platform_backend.services.user_service.domain.User;
import com.br.event_platform_backend.services.user_service.repository.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final String email = "geancardosorabelomota@gmail.com";

    public NotificationServiceImpl(NotificationRepository notificationRepository, JavaMailSender javaMailSender
            , UserRepository userRepository){
        this.notificationRepository = notificationRepository;
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
    }

    @Override
    public void sendMessage(NotificationSendDTO notificationSendDTO) {
        if(userRepository.existsById(notificationSendDTO.userId())){
            SimpleMailMessage message = new SimpleMailMessage();
            User user = userRepository.getReferenceById(notificationSendDTO.userId());
            Notification notification = Notification
                    .builder()
                    .user(user)
                    .notificationType(NotificationType.EMAIL)
                    .notificationStatus(NotificationStatus.SENT)
                    .subject(notificationSendDTO.subject())
                    .message(notificationSendDTO.message())
                    .sentAt(LocalDateTime.now())
                    .build();
            message.setFrom(email);
            message.setTo(user.getEmail());
            message.setSubject(notification.getSubject());
            message.setText(notification.getMessage());
            javaMailSender.send(message);
        }else{
            throw new UserNotFound("User find by id not found");
        }
    }

    @Override
    public List<NotificationDetailsDTO> findAll() {
        return notificationRepository.findAll().stream().map(NotificationDetailsDTO::new).toList();
    }

    @Override
    public NotificationDetailsDTO findById(UUID id) {
        if(notificationRepository.existsById(id)){
            Notification notificationDatabase = notificationRepository.getReferenceById(id);
            return new NotificationDetailsDTO(notificationDatabase);
        }
        throw new NotificationNotFound("Notification find by id not found");
    }
}
