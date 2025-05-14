package com.br.event_platform_backend.exceptions;

public class EventTicketInvalid extends RuntimeException {
    public EventTicketInvalid(String message) {
        super(message);
    }
}
