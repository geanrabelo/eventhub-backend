package com.br.event_platform_backend.services.event_service.service;

import com.br.event_platform_backend.services.event_service.dto.EventCreationDTO;
import com.br.event_platform_backend.services.event_service.dto.EventDetailsDTO;

import java.util.List;
import java.util.UUID;

public interface EventService {

    String createEvent(EventCreationDTO eventCreationDTO);

    List<EventDetailsDTO> findAll();

    EventDetailsDTO findById(UUID id);

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
