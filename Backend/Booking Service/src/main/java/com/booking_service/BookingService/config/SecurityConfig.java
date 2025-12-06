

	
	package com.booking_service.BookingService.config;

	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
	import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.booking_service.BookingService.filter.JwtFilter;

import org.springframework.beans.factory.annotation.Autowired;

	@Configuration
	@EnableWebSecurity
	public class SecurityConfig {

	    @Autowired
	    private JwtFilter jwtFilter;

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	        http.cors().and().
	        csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	            		.requestMatchers("/bookings/**").authenticated()
	                    .anyRequest().permitAll()
	            )
	            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
	}


