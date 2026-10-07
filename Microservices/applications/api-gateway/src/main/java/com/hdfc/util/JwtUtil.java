package com.hdfc.util;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final String SECRET_KEY = "myVeryLongSecretKeyForJwtAuthenticationExample123456789";

	private final SecretKey signingKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

	
	public Claims extractClaims(String token) {

		return Jwts.parser()

				.verifyWith(signingKey)

				.build()

				.parseSignedClaims(token)

				.getPayload();
	}

	public String extractEmail(String token) {

		return extractClaims(token).getSubject();
	}

	public String extractRole(String token) {

		return extractClaims(token).get("authorities", String.class);
	}

	public boolean isTokenValid(String token) {

		try {

			extractClaims(token);

			return true;

		} catch (Exception e) {

			return false;
		}
	}

}
