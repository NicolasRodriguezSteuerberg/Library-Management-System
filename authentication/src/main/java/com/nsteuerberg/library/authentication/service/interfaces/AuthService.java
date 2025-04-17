package com.nsteuerberg.library.authentication.service.interfaces;

import com.nsteuerberg.library.authentication.presentation.dto.requests.SignInRequest;
import com.nsteuerberg.library.authentication.presentation.dto.responses.SignInResponse;

public interface AuthService {
    SignInResponse login(SignInRequest signInRequest);
}
