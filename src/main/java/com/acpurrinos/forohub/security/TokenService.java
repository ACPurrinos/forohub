package com.acpurrinos.forohub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String generarToken(){
        try {
            Algorithm algorithm = Algorithm.HMAC256("1234");
            return JWT.create()
                    .withIssuer("forohub") //"auth0"
                    .withSubject("ariel@ejemplo.com")
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }

    }
}
