package com.br.event_platform_backend.event_service.auth_service.service.impl;

import com.br.event_platform_backend.event_service.auth_service.service.TokenService;
import com.br.event_platform_backend.event_service.auth_service.service.interfaces.AuthenticationService;
import com.br.event_platform_backend.user_service.domain.User;
import com.br.event_platform_backend.user_service.dto.AuthenticationDTO;
import com.br.event_platform_backend.user_service.dto.RegisterDTO;
import com.br.event_platform_backend.user_service.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepository userRepository,
                                     TokenService tokenService,
                                     AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public String login(AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.email()
                , authenticationDTO.password());
        var authentication = authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((User) authentication);
    }

    @Override
    public ResponseEntity<?> register(RegisterDTO registerDTO) {
        if(userRepository.findByEmail(registerDTO.email()) != null) return ResponseEntity.badRequest()
                .body("Already exists user with email");
        String passwordCrypt = new BCryptPasswordEncoder().encode(registerDTO.password());
        User user = new User(registerDTO.fullName(),
                registerDTO.email(),
                registerDTO.password(),
                registerDTO.userRole());
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
