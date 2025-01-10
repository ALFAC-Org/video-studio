package br.com.alfac.videostudio.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY = "secreta"; // Use uma chave mais segura em produção
    private static final long EXPIRATION_TIME = 3600000; // 1 hora em milissegundos

    // Gera um token JWT para o usuário
    public String generateToken(String username) {
        Algorithm algorithm = Algorithm.HMAC512(SECRET_KEY);

        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);
    }

    // Extrai o username do token JWT
    public String getUsernameFromToken(String token) {
        DecodedJWT decodedJWT = decodeToken(token);
        return decodedJWT.getSubject();
    }

    // Verifica se o token é válido
    public boolean validateToken(String token) {
        try {
            decodeToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Método auxiliar para decodificar o token
    private DecodedJWT decodeToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(SECRET_KEY);
        return JWT.require(algorithm)
                .build()
                .verify(token);
    }

}