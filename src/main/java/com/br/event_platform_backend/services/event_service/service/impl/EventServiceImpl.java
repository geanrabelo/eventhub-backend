package com.br.event_platform_backend.services.event_service.service.impl;

import com.br.event_platform_backend.exceptions.EventNotFound;
import com.br.event_platform_backend.exceptions.EventTicketInvalid;
import com.br.event_platform_backend.exceptions.EventTittleAlreadyExists;
import com.br.event_platform_backend.exceptions.UserNotFound;
import com.br.event_platform_backend.services.event_service.domain.Event;
import com.br.event_platform_backend.services.event_service.dto.EventCreationDTO;
import com.br.event_platform_backend.services.event_service.dto.EventDetailsDTO;
import com.br.event_platform_backend.services.event_service.repository.EventRepository;
import com.br.event_platform_backend.services.event_service.service.EventService;
import com.br.event_platform_backend.services.user_service.domain.User;
import com.br.event_platform_backend.services.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String createEvent(EventCreationDTO eventCreationDTO) {
        if(!eventRepository.existsByTittle(eventCreationDTO.tittle()) && userRepository.existsById(eventCreationDTO.userId())){
            User user = userRepository.getReferenceById(eventCreationDTO.userId());
            if(eventCreationDTO.availableTickets() <= eventCreationDTO.totalTickets()
            &&
            eventCreationDTO.availableTickets() > 0){
                Event event = Event
                        .builder()
                        .tittle(eventCreationDTO.tittle())
                        .description(eventCreationDTO.description())
                        .location(eventCreationDTO.location())
                        .startDateTime(eventCreationDTO.startDateTime())
                        .endDateTime(eventCreationDTO.endDateTime())
                        .user(user)
                        .totalTickets(eventCreationDTO.totalTickets())
                        .availableTickets(eventCreationDTO.availableTickets())
                        .price(eventCreationDTO.price())
                        .eventCategory(eventCreationDTO.eventCategory())
                        .eventStatus(eventCreationDTO.eventStatus())
                        .createdAt(LocalDateTime.now())
                        .updated_at(LocalDateTime.now())
                        .build();
                eventRepository.save(event);
                return "Event registered successfully";
            }else{
                throw new EventTicketInvalid("Tickets invalid");
            }
        } else if (eventRepository.existsByTittle(eventCreationDTO.tittle())) {
            throw new EventTittleAlreadyExists("Already exists event with this tittle");
        }else{
            throw new UserNotFound("User find by id not found");
        }
    }

    @Override
    public List<EventDetailsDTO> findAll() {
        return eventRepository.findAll().stream().map(EventDetailsDTO::new).toList();
    }

    @Override
    public EventDetailsDTO findById(UUID id) {
        if(existsById(id)){
            Event eventDatabase = eventRepository.getReferenceById(id);
            return new EventDetailsDTO(eventDatabase);
        }
        throw new EventNotFound("Event find by id not found");
    }

    @Override
    public boolean existsById(UUID id) {
        return eventRepository.existsById(id);
    }

    @Override
    public void deleteById(UUID id) {
        if(existsById(id)){
            eventRepository.deleteById(id);
        }else{
            throw new EventNotFound("Event find by id not found");
        }
    }
}
