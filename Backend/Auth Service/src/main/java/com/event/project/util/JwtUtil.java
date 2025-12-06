package com.event.project.util;



	import java.nio.charset.StandardCharsets;
	import java.security.Key;
	import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
	import org.springframework.stereotype.Component;
	import io.jsonwebtoken.*;
	import io.jsonwebtoken.security.Keys;

	@Component
	public class JwtUtil {
	    @Value("${jwt.secret}")
	    private String secret;

	    private final long EXP = 1000 * 60 * 60 * 10;

	    private Key getSigningKey() { return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); }

	    public String generateToken(String email, String role) {
	        Map<String, Object> claims = new HashMap<>();
	        claims.put("role", role);

	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(email)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
	                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
	                .compact();
	    }

	    public String extractEmail(String token) {
	        return parseClaims(token).getSubject();
	    }

	    public String extractRole(String token) {
	        return parseClaims(token).get("role", String.class);
	    }

	    private Claims parseClaims(String token) {
	        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	    }
	}

 



