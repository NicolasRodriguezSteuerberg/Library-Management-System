package com.nsteuerberg.library.authentication.presentation.controller;

import com.nsteuerberg.library.authentication.presentation.dto.requests.SignInRequest;
import com.nsteuerberg.library.authentication.presentation.dto.requests.SignUpRequest;
import com.nsteuerberg.library.authentication.presentation.dto.responses.RsaPublicKeyResponse;
import com.nsteuerberg.library.authentication.presentation.dto.responses.TokenAuthenticationResponse;
import com.nsteuerberg.library.authentication.service.exception.BadRegisterException;
import com.nsteuerberg.library.authentication.service.implementation.AuthServiceImpl;
import com.nsteuerberg.library.authentication.util.token.JwtProvider;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final JwtProvider provider;
    private final AuthServiceImpl authService;

    public AuthController(JwtProvider provider, AuthServiceImpl authService) {
        this.provider = provider;
        this.authService = authService;
    }


    @GetMapping("public-key")
    @ResponseStatus(HttpStatus.OK)
    public RsaPublicKeyResponse publicKey () {
        return provider.getPublicKeyResponse();
    }

    @PostMapping("sign-in")
    @ResponseStatus(HttpStatus.OK)
    public TokenAuthenticationResponse login(
            @RequestBody @Valid SignInRequest signInRequest,
            @RequestHeader(value = "User-Agent") String deviceId
    ){
        return authService.login(signInRequest, deviceId);
    }

    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.OK)
    public TokenAuthenticationResponse register(
            @RequestBody @Valid SignUpRequest signUpRequest,
            @RequestHeader(value = "User-Agent") String deviceId
    ) {
        if (!signUpRequest.password().equals(signUpRequest.confirmPassword())){
            throw new BadRegisterException("Passwords must match");
        }
        // ToDo only librarians can add new Members (change register to createUser)
        // ToDo sendEmail to the new Member
        // ToDo call to service that have all the content of the user
        return authService.register(signUpRequest, deviceId);
    }

    // ToDo create method to update passwords
}
