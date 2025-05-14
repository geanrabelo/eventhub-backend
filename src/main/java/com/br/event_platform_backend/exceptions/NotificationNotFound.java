package com.br.event_platform_backend.exceptions;

public class NotificationNotFound extends RuntimeException {
    public NotificationNotFound(String message) {
        super(message);
    }
}
