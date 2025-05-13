package com.br.event_platform_backend.exceptions;

public class AuthUsernameAlreadyExists extends RuntimeException {
  public AuthUsernameAlreadyExists(String message) {
    super(message);
  }
}
