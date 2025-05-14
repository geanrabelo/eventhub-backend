package com.br.event_platform_backend.config;

import com.br.event_platform_backend.exceptions.*;
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

    @ExceptionHandler(EventTittleAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse eventTittleAlreadyExistsHandler(EventTittleAlreadyExists eventTittleAlreadyExists){
        return ErrorResponse.conflict(eventTittleAlreadyExists.getMessage());
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse userNotFoundHandler(UserNotFound userNotFound){
        return ErrorResponse.notFound(userNotFound.getMessage());
    }

    @ExceptionHandler(EventNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse eventNotFoundHandler(EventNotFound eventNotFound){
        return ErrorResponse.notFound(eventNotFound.getMessage());
    }
}
