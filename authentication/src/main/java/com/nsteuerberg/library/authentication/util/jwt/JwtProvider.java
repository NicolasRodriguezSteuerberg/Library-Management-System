package com.nsteuerberg.library.authentication.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nsteuerberg.library.authentication.presentation.dto.responses.RsaPublicKeyResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

@Component
public class JwtProvider {

    @Autowired
    private final RSAPublicKey rsaPublicKey;
    @Autowired
    private final RSAPrivateKey rsaPrivateKey;
    @Getter
    private final RsaPublicKeyResponse publicKeyResponse;

    public JwtProvider(RSAPublicKey rsaPublicKey, RSAPrivateKey rsaPrivateKey) {
        this.rsaPublicKey = rsaPublicKey;
        this.rsaPrivateKey = rsaPrivateKey;
        this.publicKeyResponse = getPublicKey(rsaPublicKey);
    }

    public String createToken() {
        System.out.println(rsaPublicKey);
        System.out.println(rsaPrivateKey);
        Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
        return JWT.create()
                .sign(algorithm);
    }

    private RsaPublicKeyResponse getPublicKey(RSAPublicKey publicKet) {
        String modulus = Base64.getUrlEncoder().withoutPadding().encodeToString(publicKet.getModulus().toByteArray());
        String exponent = Base64.getUrlEncoder().withoutPadding().encodeToString(publicKet.getPublicExponent().toByteArray());
        return new RsaPublicKeyResponse("RSA", modulus, exponent);
    }
}
