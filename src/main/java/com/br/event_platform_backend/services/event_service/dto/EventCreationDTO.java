package com.br.event_platform_backend.services.event_service.dto;

import com.br.event_platform_backend.services.event_service.enums.EventCategory;
import com.br.event_platform_backend.services.event_service.enums.EventStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record EventCreationDTO(String tittle,
                               String description,
                               String location,
                               LocalDateTime startDateTime,
                               LocalDateTime endDateTime,
                               UUID userId,
                               Integer totalTickets,
                               Integer availableTickets,
                               BigDecimal price,
                               EventCategory eventCategory,
                               EventStatus eventStatus) {
}
