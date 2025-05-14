package com.br.event_platform_backend.services.ticket_service.service.impl;

import com.br.event_platform_backend.exceptions.EventNotFound;
import com.br.event_platform_backend.exceptions.TicketHaventAvailable;
import com.br.event_platform_backend.exceptions.TicketNotFound;
import com.br.event_platform_backend.exceptions.TicketUserAlreadyExists;
import com.br.event_platform_backend.services.event_service.domain.Event;
import com.br.event_platform_backend.services.event_service.repository.EventRepository;
import com.br.event_platform_backend.services.ticket_service.domain.Ticket;
import com.br.event_platform_backend.services.ticket_service.dto.TicketCreationDTO;
import com.br.event_platform_backend.services.ticket_service.dto.TicketDetailsDTO;
import com.br.event_platform_backend.services.ticket_service.enums.TicketStatus;
import com.br.event_platform_backend.services.ticket_service.repository.TicketRepository;
import com.br.event_platform_backend.services.ticket_service.service.TicketService;
import com.br.event_platform_backend.services.user_service.domain.User;
import com.br.event_platform_backend.services.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository, EventRepository eventRepository){
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public String createTicket(TicketCreationDTO ticketCreationDTO) {
        if(!ticketRepository.existsByUserId(ticketCreationDTO.userId())){
            if(eventRepository.existsById(ticketCreationDTO.eventId())){
                    Event eventExists = eventRepository.getReferenceById(ticketCreationDTO.eventId());
                    if(eventExists.getAvailableTickets() > 0){
                        User userExists = userRepository.getReferenceById(ticketCreationDTO.userId());
                        Ticket ticket = Ticket
                                .builder()
                                .event(eventExists)
                                .user(userExists)
                                .purchaseDateTime(LocalDateTime.now())
                                .ticketStatus(TicketStatus.ACTIVE)
                                .pricePaid(eventExists.getPrice())
                                .build();
                        ticketRepository.save(ticket);
                        eventExists.setAvailableTickets(eventExists.getAvailableTickets() - 1);
                        eventRepository.save(eventExists);
                        return "Ticket registered successfully";
                    }else{
                        throw new TicketHaventAvailable("Don´t have ticket available in moment");
                    }
            }
            throw new EventNotFound("Event find by id not found");
        }
        throw new TicketUserAlreadyExists("This user already have ticket");
    }

    @Override
    public List<TicketDetailsDTO> findAll() {
        return ticketRepository.findAll().stream().map(TicketDetailsDTO::new).toList();
    }

    @Override
    public TicketDetailsDTO findById(UUID id) {
        if(ticketRepository.existsById(id)){
            Ticket ticket = ticketRepository.getReferenceById(id);
            return new TicketDetailsDTO(ticket);
        }
        throw new TicketNotFound("Ticket find by id not found");
    }

    @Override
    public void deleteById(UUID id) {
        if(ticketRepository.existsById(id)){
            ticketRepository.deleteById(id);
        }else{
            throw new TicketNotFound("Ticket find by id not found");
        }
    }
}
