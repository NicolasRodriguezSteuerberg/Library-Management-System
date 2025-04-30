package com.nsteuerberg.library.authentication.presentation.advice.responses;

import java.util.Map;

public record GlobalExceptionResponse(
        String type,
        Map<String, String> errors
) {
}
