package com.example.payment.filter;


	


	import com.example.payment.util.JwtUtil;
	import io.jsonwebtoken.JwtException;
	import jakarta.servlet.FilterChain;
	import jakarta.servlet.ServletException;
	import jakarta.servlet.http.*;
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
	import org.springframework.web.filter.OncePerRequestFilter;
	import java.io.IOException;
import java.util.List;

	@Component
	public class JwtFilter extends OncePerRequestFilter {

	    @Autowired
	    private JwtUtil jwtUtil;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain filterChain) throws ServletException, IOException {

	        String header = request.getHeader("Authorization");
	        if (header != null && header.startsWith("Bearer ")) {
	            String token = header.substring(7);
	            try {
	                String email = jwtUtil.extractEmail(token);
	                String role = jwtUtil.extractRole(token);
	                
	                request.setAttribute("email", email);
	                request.setAttribute("role", role);
	                
	                UsernamePasswordAuthenticationToken authentication =
	                        new UsernamePasswordAuthenticationToken(email, null, List.of());

	                SecurityContextHolder.getContext().setAuthentication(authentication);
	            } catch (JwtException ex) {
	                
	                logger.warn("Invalid JWT: " + ex.getMessage());
	            }
	        }
	        filterChain.doFilter(request, response);
	    }
	}



