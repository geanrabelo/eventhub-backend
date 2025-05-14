package com.br.event_platform_backend.services.ticket_service.service;

import com.br.event_platform_backend.services.ticket_service.dto.TicketCreationDTO;
import com.br.event_platform_backend.services.ticket_service.dto.TicketDetailsDTO;

import java.util.List;
import java.util.UUID;

public interface TicketService {

    String createTicket(TicketCreationDTO ticketCreationDTO);

    List<TicketDetailsDTO> findAll();

    TicketDetailsDTO findById(UUID id);

    void deleteById(UUID id);
}
