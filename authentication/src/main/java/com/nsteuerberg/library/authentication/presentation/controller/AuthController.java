package com.nsteuerberg.library.authentication.presentation.controller;

import com.nsteuerberg.library.authentication.presentation.dto.requests.SignInRequest;
import com.nsteuerberg.library.authentication.presentation.dto.requests.SignUpRequest;
import com.nsteuerberg.library.authentication.presentation.dto.responses.RsaPublicKeyResponse;
import com.nsteuerberg.library.authentication.presentation.dto.responses.SignInResponse;
import com.nsteuerberg.library.authentication.presentation.dto.responses.SignUpResponse;
import com.nsteuerberg.library.authentication.util.jwt.JwtProvider;
import jakarta.validation.Valid;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private JwtProvider provider;

    @GetMapping("public-key")
    @ResponseStatus(HttpStatus.OK)
    public RsaPublicKeyResponse publicKey () {
        return provider.getPublicKeyResponse();
    }

    @PostMapping("sign-in")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponse login(@RequestBody @Valid SignInRequest signInRequest){
        System.out.println(provider.createToken());
        return SignInResponse.builder()
                .accessToken(signInRequest.username())
                .refreshToken(signInRequest.password())
                .build();
    }

    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.OK)
    public SignUpResponse register(@RequestBody @Valid SignUpRequest signUpRequest) {
        return null;
    }
}
