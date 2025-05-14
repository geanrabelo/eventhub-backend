package com.br.event_platform_backend.services.auth_service.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.event_platform_backend.services.user_service.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${security.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-service")
                    .withExpiresAt(generationDateExpire())
                    .withSubject(user.getUsername())
                    .withClaim("roles", user.getUserRole().name())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            return "Error while generating token";
        }
    }

    public DecodedJWT validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-service")
                    .build()
                    .verify(token);
        }catch (JWTVerificationException e){
            e.printStackTrace();
            return null;
        }
    }


    private Instant generationDateExpire(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
