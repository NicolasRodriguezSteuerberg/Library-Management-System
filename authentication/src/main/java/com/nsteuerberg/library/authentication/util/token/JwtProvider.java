package com.nsteuerberg.library.authentication.util.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nsteuerberg.library.authentication.presentation.dto.responses.RsaPublicKeyResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.List;

@Component
public class JwtProvider {

    @Value("${jwt.with-user}")
    private String userGenerator;

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

    public String createToken(Authentication authentication) {
        String username = authentication.getPrincipal().toString();
        List<String> authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
        return JWT.create()
                .withIssuer(userGenerator)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .sign(algorithm);
    }

    private RsaPublicKeyResponse getPublicKey(RSAPublicKey publicKet) {
        String modulus = Base64.getUrlEncoder().withoutPadding().encodeToString(publicKet.getModulus().toByteArray());
        String exponent = Base64.getUrlEncoder().withoutPadding().encodeToString(publicKet.getPublicExponent().toByteArray());
        return new RsaPublicKeyResponse("RSA", modulus, exponent);
    }
}
