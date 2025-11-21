package br.com.anjos_protetores_de_animais.api_controle_adocoes.config.security;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withSubject(user.getEmail())
                    .withClaim("role", user.getRole().name())
                    .withExpiresAt(generationExpirationDate())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTCreationException e) {
            return "";
        }
    }

    public Instant generationExpirationDate() {
        return LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-03:00"));
    }
}