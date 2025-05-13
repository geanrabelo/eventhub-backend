package com.br.event_platform_backend.config;

import com.br.event_platform_backend.exceptions.AuthEmailAlreadyExists;
import com.br.event_platform_backend.exceptions.AuthUsernameAlreadyExists;
import com.br.event_platform_backend.exceptions.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthEmailAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse authEmailAlreadyExistsHandler(AuthEmailAlreadyExists authEmailAlreadyExists){
        return ErrorResponse.conflict(authEmailAlreadyExists.getMessage());
    }

    @ExceptionHandler(AuthUsernameAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse authUsernameAlreadyExistsHandler(AuthUsernameAlreadyExists authUsernameAlreadyExists){
        return ErrorResponse.conflict(authUsernameAlreadyExists.getMessage());
    }

}
