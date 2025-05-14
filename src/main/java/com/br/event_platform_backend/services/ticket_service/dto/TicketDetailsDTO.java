package com.br.event_platform_backend.services.ticket_service.dto;

import com.br.event_platform_backend.services.ticket_service.domain.Ticket;
import com.br.event_platform_backend.services.ticket_service.enums.TicketStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TicketDetailsDTO(UUID id,
                               UUID eventId,
                               UUID userId,
                               LocalDateTime purchaseDateTime,
                               TicketStatus ticketStatus,
                               BigDecimal pricePaid) {
    public TicketDetailsDTO(Ticket ticket){
        this(
                ticket.getId(),
                ticket.getEvent().getId(),
                ticket.getUser().getId(),
                ticket.getPurchaseDateTime(),
                ticket.getTicketStatus(),
                ticket.getPricePaid()
        );
    }
}
