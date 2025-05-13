package com.br.event_platform_backend.exceptions;

public class AuthEmailAlreadyExists extends RuntimeException {
    public AuthEmailAlreadyExists(String message) {
        super(message);
    }
}
