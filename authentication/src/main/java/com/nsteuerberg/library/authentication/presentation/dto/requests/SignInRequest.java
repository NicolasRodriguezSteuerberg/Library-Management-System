package com.nsteuerberg.library.authentication.presentation.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record SignInRequest(
        @NotBlank String username,
        @NotBlank String password
){
}
