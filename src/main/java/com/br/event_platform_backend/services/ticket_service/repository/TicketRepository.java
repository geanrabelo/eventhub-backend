package com.br.event_platform_backend.services.ticket_service.repository;

import com.br.event_platform_backend.services.ticket_service.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    boolean existsByUserId(UUID id);


}
