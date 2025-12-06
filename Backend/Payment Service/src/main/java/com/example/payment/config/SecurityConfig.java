package com.example.payment.config;



	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.annotation.*;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.web.SecurityFilterChain;
	import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.payment.filter.JwtFilter;

	@Configuration
	public class SecurityConfig {

	    @Autowired
	    private JwtFilter jwtFilter;

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http.cors().and().csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/payment/**").authenticated()
	                .anyRequest().permitAll()
	            )
	            // add JwtFilter before username/password filter
	            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
	}


