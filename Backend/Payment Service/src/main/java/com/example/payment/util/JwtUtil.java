

	package com.example.payment.util;

	import java.nio.charset.StandardCharsets;
	import java.security.Key;
	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.stereotype.Component;
	import io.jsonwebtoken.*;
	import io.jsonwebtoken.security.Keys;

	@Component
	public class JwtUtil {

	    @Value("${jwt.secret}")
	    private String secret;

	    private Key getSigningKey() {
	        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	    }

	    public String extractEmail(String token) {
	        Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	        return claims.getSubject();
	    }

	    public String extractRole(String token) {
	        Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	        return claims.get("role", String.class);
	    }
	}


