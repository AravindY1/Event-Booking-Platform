package com.event_service.Project.util;



	import java.security.Key;
import java.util.Date;
	import io.jsonwebtoken.security.Keys;



	import org.springframework.stereotype.Component;

	import io.jsonwebtoken.Claims;
	import io.jsonwebtoken.Jwts;
	import io.jsonwebtoken.SignatureAlgorithm;



	import java.nio.charset.StandardCharsets;

	
	import org.springframework.beans.factory.annotation.Value;


	@Component
	public class JwtUtil {
	    @Value("${jwt.secret}")
	    private String secret;

	    private final long EXP = 1000 * 60 * 60 * 10;

	    private Key getSigningKey() { return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); }

	    public String generateToken(String email, String role) {
	        return Jwts.builder()
	                .setSubject(email)
	                .claim("role", role)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + EXP))
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


	    
	    




