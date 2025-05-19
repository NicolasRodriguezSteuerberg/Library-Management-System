package com.nsteuerber.library.books.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nsteuerber.library.books.service.http.auth.IAuthClient;
import com.nsteuerber.library.books.service.http.auth.response.RsaPublicKeyResponse;
import com.nsteuerber.library.books.service.interfaces.IAuthService;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {
    //ToDo descomentar
    //private final IAuthClient securityClient;

    private RSAPublicKey publicKey;

    private final String TOKEN_START_WITH = "Bearer ";

//    public AuthServiceImpl(IAuthClient securityClient) {
//        this.securityClient = securityClient;
//        getPublicKey();
//    }

    @Override
    public RSAPublicKey getPublicKey(){
        if (this.publicKey == null){
            try {
                this.publicKey = buildPublicKey();
            } catch (Exception e) {
                throw new InternalException("Error recogiendo la clave pública: " + e.getMessage());
            }
        }
        return this.publicKey;
    }

    private RSAPublicKey buildPublicKey()  throws NoSuchAlgorithmException, InvalidKeySpecException {
        // ToDo descomentar
        //RsaPublicKeyResponse publicKeyResponse = securityClient.getPublicKey();
        RsaPublicKeyResponse publicKeyResponse = null;

        // decodificamos la base 64 del modulus y exponent
        BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(publicKeyResponse.n()));
        BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(publicKeyResponse.e()));

        return (RSAPublicKey) KeyFactory
                .getInstance(publicKeyResponse.kty())
                .generatePublic(new RSAPublicKeySpec(modulus, exponent));
    }

    @Override
    public DecodedJWT validateToken(String token) {
        try {
            RSAPublicKey rsaPublicKey = getPublicKey();
            if (token.startsWith(TOKEN_START_WITH)) token = token.substring(TOKEN_START_WITH.length());
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, null);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (JWTVerificationException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public String extractUsername(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }

    @Override
    public Set<GrantedAuthority> authorities(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("authorities")
                .asList(String.class)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
