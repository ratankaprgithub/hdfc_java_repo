package com.hdfc.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final String SECRET_KEY = "myVeryLongSecretKeyForJwtAuthenticationExample123456789";

	private SecretKey getSigningKey() {

		SecretKey skey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
		return skey;
	}
	
	
	public String generateToken(String email, String role) {

		
		
		
		return Jwts.builder()
				.issuer("HDFC")

				.subject(email) 
				
				.claim("authorities", role)

				.issuedAt(new Date())

				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 min

				.signWith(getSigningKey())

				.compact();
	}
}