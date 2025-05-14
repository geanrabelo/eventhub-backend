package com.br.event_platform_backend.exceptions;

public class EventTittleAlreadyExists extends RuntimeException {
    public EventTittleAlreadyExists(String message) {
        super(message);
    }
}
