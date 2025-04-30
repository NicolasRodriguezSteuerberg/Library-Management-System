package com.nsteuerberg.library.authentication.presentation.dto.responses;

public record ErrorExceptionResponse (
        String error,
        String message
) {
}
