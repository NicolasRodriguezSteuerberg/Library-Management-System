package com.nsteuerberg.library.authentication.presentation.advice;

import com.nsteuerberg.library.authentication.presentation.advice.responses.AuthExceptionResponse;
import com.nsteuerberg.library.authentication.presentation.advice.responses.GlobalExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GlobalExceptionResponse methodArgumentNotValid (MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new GlobalExceptionResponse(
                "Validation",
                errors
        );
    }
}
