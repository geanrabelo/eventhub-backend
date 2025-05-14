package com.br.event_platform_backend.services.event_service.dto;

import com.br.event_platform_backend.services.event_service.domain.Event;
import com.br.event_platform_backend.services.event_service.enums.EventCategory;
import com.br.event_platform_backend.services.event_service.enums.EventStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record EventDetailsDTO(UUID id,
                              String tittle,
                              String description,
                              String location,
                              LocalDateTime startDateTime,
                              LocalDateTime endDateTime,
                              UUID userId,
                              Integer totalTickets,
                              Integer availableTickets,
                              BigDecimal price,
                              EventCategory eventCategory,
                              EventStatus eventStatus,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt) {
    public EventDetailsDTO(Event event){
        this(
                event.getId(),
                event.getTittle(),
                event.getDescription(),
                event.getLocation(),
                event.getStartDateTime(),
                event.getEndDateTime(),
                event.getUser().getId(),
                event.getTotalTickets(),
                event.getAvailableTickets(),
                event.getPrice(),
                event.getEventCategory(),
                event.getEventStatus(),
                event.getCreatedAt(),
                event.getUpdated_at()
        );
    }
}
