package com.nsteuerberg.library.authentication.presentation.dto.responses;

public record TokenAuthenticationResponse (
        String refreshToken,
        String accessToken
){
}
