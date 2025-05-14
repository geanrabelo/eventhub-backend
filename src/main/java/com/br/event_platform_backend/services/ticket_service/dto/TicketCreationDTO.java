package com.br.event_platform_backend.services.ticket_service.dto;

import com.br.event_platform_backend.services.ticket_service.enums.TicketStatus;

import java.util.UUID;

public record TicketCreationDTO(UUID eventId,
                                UUID userId
) {
}
