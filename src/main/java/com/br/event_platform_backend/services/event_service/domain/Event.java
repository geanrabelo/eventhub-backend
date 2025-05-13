package com.br.event_platform_backend.services.event_service.domain;

import com.br.event_platform_backend.services.event_service.enums.EventCategory;
import com.br.event_platform_backend.services.event_service.enums.EventStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_event")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private String location;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

    //referencia a usuario

    @Column(name = "total_tickets")
    private Integer totalTickets;

    @Column(name = "available_tickets")
    private Integer availableTickets;

    private BigDecimal price;

    @Column(name = "event_category")
    @Enumerated(EnumType.STRING)
    private EventCategory eventCategory;

    @Column(name = "event_status")
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}
