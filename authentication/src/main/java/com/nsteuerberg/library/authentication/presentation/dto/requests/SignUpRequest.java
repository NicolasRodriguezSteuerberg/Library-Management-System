package com.nsteuerberg.library.authentication.presentation.dto.requests;

public record SignUpRequest(
        String username,
        String password,
        String confirmPassword
) {
}
