package com.br.event_platform_backend.services.auth_service.service.interfaces;

import com.br.event_platform_backend.services.user_service.dto.AuthenticationDTO;
import com.br.event_platform_backend.services.user_service.dto.RegisterDTO;
import com.br.event_platform_backend.services.user_service.dto.UserDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AuthenticationService {

    String login(AuthenticationDTO authenticationDTO);

    void register(RegisterDTO registerDTO);

    List<UserDetailsDTO> findAll();

    UserDetailsDTO findById(UUID id);

    void deleteById(UUID id);
}
