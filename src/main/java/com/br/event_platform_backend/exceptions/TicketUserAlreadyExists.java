package com.br.event_platform_backend.exceptions;

public class TicketUserAlreadyExists extends RuntimeException {
    public TicketUserAlreadyExists(String message) {
        super(message);
    }
}
