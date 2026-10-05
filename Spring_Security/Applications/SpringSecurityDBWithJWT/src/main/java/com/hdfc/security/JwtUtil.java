
package com.hdfc.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final String SECRET_KEY = "myVeryLongSecretKeyForJwtAuthenticationExample123456789";

	private SecretKey getSigningKey() {

		SecretKey skey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
		return skey;
	}

	//11:10
	
	
	public String generateToken(Authentication authentication) {

		return Jwts.builder()

				.issuer("HDFC")

				.subject(authentication.getName()) // username i.e email

				.issuedAt(new Date())

				.claim("companyname", "HDFC Life")
				
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 min

				.signWith(getSigningKey())

				.compact();
	}

	// To verify the valid token and if token in valid extract the claims
	public Claims extractClaims(String token) {

		return Jwts.parser()

				.verifyWith(getSigningKey())

				.build()

				.parseSignedClaims(token)

				.getPayload();
	}

	// Extract Username
	public String extractUsername(String token) {

		return extractClaims(token).getSubject();
	}

	// Check whether token is expired
	public boolean isTokenExpired(String token) {

		Date expDate = extractClaims(token).getExpiration();

		return expDate.before(new Date());
	}

	// Validate Token
	public boolean validateToken(String token) {

		try {
			return !isTokenExpired(token);
		} catch (Exception e) {
			return false;
		}
	}
}
