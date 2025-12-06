package com.booking_service.BookingService.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;



	    
@Component
public class JwtUtil {
  @Value("${jwt.secret}")
  private String secret;
  private Key getSigningKey() { return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); }
  public String extractEmail(String token) {
    return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
  }
  public String extractRole(String token) { return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().get("role", String.class); }
}


	



