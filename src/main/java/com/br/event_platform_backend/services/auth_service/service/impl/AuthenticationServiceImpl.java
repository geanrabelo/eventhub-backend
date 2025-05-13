package com.br.event_platform_backend.services.auth_service.service.impl;

import com.br.event_platform_backend.exceptions.AuthEmailAlreadyExists;
import com.br.event_platform_backend.exceptions.AuthUsernameAlreadyExists;
import com.br.event_platform_backend.services.auth_service.service.TokenService;
import com.br.event_platform_backend.services.auth_service.service.interfaces.AuthenticationService;
import com.br.event_platform_backend.services.user_service.domain.User;
import com.br.event_platform_backend.services.user_service.dto.AuthenticationDTO;
import com.br.event_platform_backend.services.user_service.dto.RegisterDTO;
import com.br.event_platform_backend.services.user_service.repository.UserRepository;
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
    public void register(RegisterDTO registerDTO) {
        if(userRepository.existsByEmail(registerDTO.email())){
            throw new AuthEmailAlreadyExists("Already exists this email in database");
        } else if (userRepository.existsByUsername(registerDTO.username())) {
            throw new AuthUsernameAlreadyExists("Already exists this username in database");
        }else {
            String passwordCrypt = new BCryptPasswordEncoder().encode(registerDTO.password());
            User user = new User(registerDTO.fullName(),
                    registerDTO.email(),
                    registerDTO.username(),
                    passwordCrypt,
                    registerDTO.userRole());
            userRepository.save(user);
        }
    }
}
