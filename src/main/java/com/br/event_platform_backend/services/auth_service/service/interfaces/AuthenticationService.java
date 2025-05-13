package com.br.event_platform_backend.services.auth_service.service.interfaces;

import com.br.event_platform_backend.services.user_service.dto.AuthenticationDTO;
import com.br.event_platform_backend.services.user_service.dto.RegisterDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    String login(AuthenticationDTO authenticationDTO);

    void register(RegisterDTO registerDTO);
}
