package com.br.event_platform_backend.exceptions;

public class TicketHaventAvailable extends RuntimeException {
    public TicketHaventAvailable(String message) {
        super(message);
    }
}
