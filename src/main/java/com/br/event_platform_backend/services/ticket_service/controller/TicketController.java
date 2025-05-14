package com.br.event_platform_backend.services.ticket_service.controller;

import com.br.event_platform_backend.services.auth_service.dto.MessageDTO;
import com.br.event_platform_backend.services.ticket_service.dto.TicketCreationDTO;
import com.br.event_platform_backend.services.ticket_service.dto.TicketDetailsDTO;
import com.br.event_platform_backend.services.ticket_service.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/platform/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MessageDTO> createTicket(@RequestBody @Validated TicketCreationDTO ticketCreationDTO){
        String message = ticketService.createTicket(ticketCreationDTO);
        return ResponseEntity.ok(new MessageDTO(message));
    }

    @GetMapping
    public ResponseEntity<List<TicketDetailsDTO>> findAll(){
        return ResponseEntity.ok(ticketService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<TicketDetailsDTO> findById(@RequestParam(name = "id") String id){
        return ResponseEntity.ok(ticketService.findById(UUID.fromString(id)));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> deleteById(@RequestParam(name = "id") String id){
        ticketService.deleteById(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
