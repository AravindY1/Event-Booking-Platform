package com.booking_service.BookingService.filter;


	
	


	import com.booking_service.BookingService.util.JwtUtil;
	import jakarta.servlet.FilterChain;
	import jakarta.servlet.ServletException;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
	import org.springframework.web.filter.OncePerRequestFilter;
	import io.jsonwebtoken.JwtException;

	import java.io.IOException;
import java.util.List;

	@Component
	public class JwtFilter extends OncePerRequestFilter {

	    @Autowired
	    private JwtUtil jwtUtil;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain filterChain)
	            throws ServletException, IOException {

	        String header = request.getHeader("Authorization");

	        if (header != null && header.startsWith("Bearer ")) {
	            String token = header.substring(7);

	            try {
	                String email = jwtUtil.extractEmail(token);
	                request.setAttribute("email", email);
	                String role = jwtUtil.extractRole(token);
	                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
	                        email, null, List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())));
	                
	                SecurityContextHolder.getContext().setAuthentication(auth);
	            } catch (JwtException e) {
	                System.out.println("Invalid JWT: " + e.getMessage());
	            }
	        }

	        filterChain.doFilter(request, response);
	    }
	}





