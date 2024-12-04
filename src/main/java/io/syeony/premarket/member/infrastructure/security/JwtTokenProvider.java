package io.syeony.premarket.member.infrastructure.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.syeony.premarket.member.domain.model.AuthorizationToken;
import io.syeony.premarket.member.domain.processor.util.AuthorizationTokenProvider;

@Component
public class JwtTokenProvider implements AuthorizationTokenProvider {

	private final String secretKey;
	public static final Integer EXPIRATION_TIME = 8640000;

	public JwtTokenProvider(
		@Value("${jwt.secret}") String secretKey) {
		this.secretKey = secretKey;
	}

	@Override
	public AuthorizationToken generateAuthorizeToken(String email) {
		return new AuthorizationToken(generateToken(email), generateToken(email));
	}

	public String generateToken(String email) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, email);
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
			.parser()
			.verifyWith(getSignKey())
			.build()
			.parseSignedClaims(token)
			.getPayload();
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public boolean validateToken(String token, String username) {
		final String extractedUsername = extractUsername(token);
		return extractedUsername.equals(username) && !isTokenExpired(token);
	}

	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder()
			.claims(claims)
			.subject(username)
			.issuedAt(new Date(System.currentTimeMillis()))
			.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
			.signWith(getSignKey())
			.compact();
	}

	private SecretKey getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
