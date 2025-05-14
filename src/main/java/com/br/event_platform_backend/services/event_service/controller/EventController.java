package com.br.event_platform_backend.services.event_service.controller;

import com.br.event_platform_backend.services.auth_service.dto.MessageDTO;
import com.br.event_platform_backend.services.event_service.dto.EventCreationDTO;
import com.br.event_platform_backend.services.event_service.dto.EventDetailsDTO;
import com.br.event_platform_backend.services.event_service.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/platform/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MessageDTO> createEvent(@RequestBody @Validated EventCreationDTO eventCreationDTO){
        String message = eventService.createEvent(eventCreationDTO);
        return ResponseEntity.ok(new MessageDTO(message));
    }

    @GetMapping
    public ResponseEntity<List<EventDetailsDTO>> findAll(){
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<EventDetailsDTO> findById(@RequestParam(name = "id") String id){
        return ResponseEntity.ok(eventService.findById(UUID.fromString(id)));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> deleteById(@RequestParam(name = "id") String id){
        eventService.deleteById(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
