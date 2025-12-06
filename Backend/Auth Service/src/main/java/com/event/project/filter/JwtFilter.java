package com.event.project.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.event.project.util.JwtUtil;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException, java.io.IOException {
    	
    	System.out.println("---- JwtFilter triggered for: " + request.getRequestURI());

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
           

            try {
            	
            	
            	 String token = header.substring(7);
                String role = jwtUtil.extractRole(token);
                String email = jwtUtil.extractEmail(token);
                
                System.out.println("Decoded ROLE = " + role);
                System.out.println("Decoded EMAIL = " + email);
                
                
                request.setAttribute("role", role);
                request.setAttribute("email", email);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception ignored) {
            	
            	System.out.println("Token parse FAILED: " + ignored.getMessage());
            }
        }else {
        	
        	System.out.println("No Authorization header found.");
        }

        chain.doFilter(request, response);
    }
}




