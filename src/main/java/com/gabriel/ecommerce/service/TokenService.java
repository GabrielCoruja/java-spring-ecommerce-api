package com.gabriel.ecommerce.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Class to manager actions with token JWT.
 */
@Service
public class TokenService {

  private final Algorithm algorithm;

  public TokenService(@Value("${api.security.token.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Generante new token JWT.
   */
  public String generateToken(String username) {
    return JWT.create()
        .withIssuer("ecommercedb")
        .withSubject(username)
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
  }

  /**
   * Verify token JWT.
   */
  public String validateToken(String token) {
    return JWT.require(algorithm)
        .withIssuer("ecommercedb")
        .build()
        .verify(token)
        .getSubject();
  }

  /**
   * Create time to use to expiration token JWT.
   */
  private Instant generateExpirationDate() {
    return Instant.now()
        .plus(2, ChronoUnit.HOURS);
  }
}
