package com.br.event_platform_backend.services.notification_service.domain;

import com.br.event_platform_backend.services.notification_service.enums.NotificationStatus;
import com.br.event_platform_backend.services.notification_service.enums.NotificationType;
import com.br.event_platform_backend.services.user_service.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_notification")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type")
    private NotificationType notificationType;

    private String subject;

    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_status")
    private NotificationStatus notificationStatus;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

}
