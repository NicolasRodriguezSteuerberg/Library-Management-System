package com.nsteuerber.library.books.service.http.auth.response;

public record RsaPublicKeyResponse (
        String kty,
        String n,
        String e
){
}
