package com.nsteuerberg.library.authentication.presentation.advice;

import com.nsteuerberg.library.authentication.presentation.dto.responses.ErrorExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorExceptionResponse usernameNotFoundException(UsernameNotFoundException exception) {
        return new ErrorExceptionResponse(
                "Authentication",
                exception.getMessage()
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorExceptionResponse badCredentialsException(BadCredentialsException exception) {
        return new ErrorExceptionResponse(
                "Authentication",
                exception.getMessage()
        );
    }


}
