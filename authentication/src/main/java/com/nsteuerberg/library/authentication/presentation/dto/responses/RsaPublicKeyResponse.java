package com.nsteuerberg.library.authentication.presentation.dto.responses;

public record RsaPublicKeyResponse (
        String kty,
        String n,
        String e
){
}
