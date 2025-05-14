package com.br.event_platform_backend.exceptions;

public class EventNotFound extends RuntimeException {
    public EventNotFound(String message) {
        super(message);
    }
}
