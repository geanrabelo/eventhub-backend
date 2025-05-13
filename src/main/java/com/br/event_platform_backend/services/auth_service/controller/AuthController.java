package com.br.event_platform_backend.services.auth_service.controller;

import com.br.event_platform_backend.services.auth_service.dto.MessageDTO;
import com.br.event_platform_backend.services.auth_service.service.interfaces.AuthenticationService;
import com.br.event_platform_backend.services.user_service.dto.AuthenticationDTO;
import com.br.event_platform_backend.services.user_service.dto.RegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/platform/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<MessageDTO> login(@RequestBody @Validated AuthenticationDTO authenticationDTO) {
        String message = authenticationService.login(authenticationDTO);
        return ResponseEntity.ok(new MessageDTO(message));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<MessageDTO> register(@RequestBody @Validated RegisterDTO registerDTO){
        authenticationService.register(registerDTO);
        return ResponseEntity.ok(new MessageDTO("User registered successfully"));
    }
}
