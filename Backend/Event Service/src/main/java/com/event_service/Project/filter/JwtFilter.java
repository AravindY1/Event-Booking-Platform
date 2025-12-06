package com.event_service.Project.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.event_service.Project.util.JwtUtil;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtFilter extends OncePerRequestFilter {
  @Autowired JwtUtil jwtUtil;
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
    String h = req.getHeader("Authorization");
    if (h != null && h.startsWith("Bearer ")) {
      String token = h.substring(7);
      try {
        String role = jwtUtil.extractRole(token);
        String email = jwtUtil.extractEmail(token);
        req.setAttribute("role", role);
        req.setAttribute("email", email);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                email, null, List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())));
        SecurityContextHolder.getContext().setAuthentication(auth);
      } catch (JwtException e) {
       
      }
    }
    chain.doFilter(req, res);
  }
  
  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
      String path = request.getServletPath();

      return path.startsWith("/auth/")
          || path.startsWith("/events/internal/")
          || path.equals("/events")         // if using GET /events
          || path.startsWith("/events/id/"); // allow event details
  }

  
  
}



