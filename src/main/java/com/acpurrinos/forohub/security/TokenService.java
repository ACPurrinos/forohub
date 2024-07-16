package com.acpurrinos.forohub.security;

import com.acpurrinos.forohub.models.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;
    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("forohub") //"auth0"
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpriacion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }

    }
/*
    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("forohub")
                    // reusable verifier instance
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if(decodedJWT.getSubject() != null)
            {
                return decodedJWT.getSubject();
            } else {
                return null;
            }
        } catch (JWTVerificationException exception){
            return null;
        }
    }*/
public String getSubject(String token) {
    if (token ==null){
        throw new RuntimeException();
    }
    try {
        Algorithm algorithm = Algorithm.HMAC256(apiSecret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("forohub")
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT.getSubject();
    } catch (JWTVerificationException | IllegalArgumentException exception) {
        // Captura de excepciones extendida para incluir IllegalArgumentException
        return null;
    }
}


    private Instant generarFechaExpriacion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
