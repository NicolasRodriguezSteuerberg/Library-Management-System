package com.nsteuerber.library.books.service.http.auth;

import com.nsteuerber.library.books.service.http.auth.response.RsaPublicKeyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// ToDO descomentar
//@FeignClient(
//        name = "authentication",
//        path = "localhost:8080/api/auth"
//)
public interface IAuthClient {
    @GetMapping("public-key")
    RsaPublicKeyResponse getPublicKey();
}
