package io.syeony.premarket.support.security;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {

	private static final Integer EXPIRATION_TIME = 8640000;
	private final String secret;

	public TokenProvider(@Value("${jwt.secret}") String secret) {
		this.secret = secret;
	}

	public String generateToken(String email) {
		Instant now = Instant.now();
		byte[] secretKeyBytes = Base64.getEncoder()
			.encode(Objects.requireNonNull(secret).getBytes(StandardCharsets.UTF_8));

		return Jwts.builder()
			.subject(email)
			.expiration(Date.from(now.plusSeconds(EXPIRATION_TIME)))
			.issuedAt(Date.from(now))
			.signWith(Keys.hmacShaKeyFor(secretKeyBytes))
			.compact();
	}
}
