package com.Foro.Hubapi.Alex.SEGURIDAD;

import com.Foro.Hubapi.Alex.MODELOS.USUARIO;
import com.auth0.jwt.JWT;
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
public class SERVICIO_TOKEN {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(USUARIO usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("alex api")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion(24))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
            try {
                Algorithm algorithm = Algorithm.HMAC256(apiSecret);
                verifier = JWT.require(algorithm)
                        .withIssuer("alex api")
                        .build()
                        .verify(token);
                verifier.getSubject();
            } catch (JWTVerificationException exception) {
                throw new RuntimeException();
            }
            return verifier.getSubject();
    }

    private Instant generarFechaExpiracion(Integer numeroHrs) {
        return LocalDateTime.now().plusHours(numeroHrs).toInstant(ZoneOffset.of("-06:00"));
    }
}
