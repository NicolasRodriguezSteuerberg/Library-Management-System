package com.nsteuerberg.library.authentication.presentation.advice.responses;

public record AuthExceptionResponse(
        String type,
        String message
) {
}
