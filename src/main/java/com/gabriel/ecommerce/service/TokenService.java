package com.gabriel.ecommerce.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The type Token service.
 */
@Service
public class TokenService {

  private final Algorithm algorithm;

  /**
   * Instantiates a new Token service.
   *
   * @param secret the secret
   */
  public TokenService(@Value("${api.security.token.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Generate token string.
   *
   * @param username the username
   * @return the string
   */
  public String generateToken(String username) {
    return JWT.create()
        .withIssuer("ecommercedb")
        .withSubject(username)
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
  }

  /**
   * Validate token string.
   *
   * @param token the token
   * @return the string
   */
  public String validateToken(String token) {
    return JWT.require(algorithm)
        .withIssuer("ecommercedb")
        .build()
        .verify(token)
        .getSubject();
  }

  private Instant generateExpirationDate() {
    return Instant.now()
        .plus(24, ChronoUnit.HOURS);
  }
}
