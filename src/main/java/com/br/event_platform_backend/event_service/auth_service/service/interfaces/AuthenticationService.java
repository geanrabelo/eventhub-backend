package com.br.event_platform_backend.event_service.auth_service.service.interfaces;

import com.br.event_platform_backend.user_service.dto.AuthenticationDTO;
import com.br.event_platform_backend.user_service.dto.RegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    String login(AuthenticationDTO authenticationDTO);

    ResponseEntity<?> register(RegisterDTO registerDTO);
}
