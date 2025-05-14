package com.br.event_platform_backend.config;

import com.br.event_platform_backend.services.auth_service.filter.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter){
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,  "/v1/platform/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/v1/platform/event/**").hasAnyRole("ADMIN", "ORGANIZER")
                        .requestMatchers(HttpMethod.GET, "/v1/platform/event/**").hasAnyRole("ADMIN", "ORGANIZER", "CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE,"/v1/platform/event/**").hasAnyRole("ADMIN", "ORGANIZER")
                        .requestMatchers(HttpMethod.POST, "/v1/platform/ticket/**").hasAnyRole("ADMIN", "ORGANIZER")
                        .requestMatchers(HttpMethod.GET, "/v1/platform/ticket/**").hasAnyRole("ADMIN", "ORGANIZER", "CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE,"/v1/platform/ticket/**").hasAnyRole("ADMIN", "ORGANIZER")

                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
