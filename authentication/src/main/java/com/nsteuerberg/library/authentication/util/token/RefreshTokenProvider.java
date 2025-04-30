package com.nsteuerberg.library.authentication.util.token;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RefreshTokenProvider {

    @Bean
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}
