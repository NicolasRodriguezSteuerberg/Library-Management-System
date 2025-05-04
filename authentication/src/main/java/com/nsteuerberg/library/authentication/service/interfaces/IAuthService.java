package com.nsteuerberg.library.authentication.service.interfaces;

import com.nsteuerberg.library.authentication.presentation.dto.requests.SignInRequest;
import com.nsteuerberg.library.authentication.presentation.dto.requests.SignUpRequest;
import com.nsteuerberg.library.authentication.presentation.dto.responses.TokenAuthenticationResponse;

public interface IAuthService {
    TokenAuthenticationResponse login(SignInRequest signInRequest, String deviceId);

    TokenAuthenticationResponse register(SignUpRequest signUpRequest, String deviceId);

    TokenAuthenticationResponse refreshTokens(String refreshToken, String deviceId);
}
