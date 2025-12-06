package com.event_service.Project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.event_service.Project.filter.JwtFilter;


	

	@Configuration
	@EnableWebSecurity
	public class SecurityConfig {
		
		
		@Autowired 
		private JwtFilter jwtFilter;
		
		
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http
	                .cors().and()
	                .csrf(csrf -> csrf.disable())   // disable CSRF for APIs
	                .authorizeHttpRequests(auth -> auth
	                        .requestMatchers("/auth/register", "/auth/login").permitAll()
	                        .requestMatchers("/events/internal/**").permitAll()
	                        .requestMatchers("/events/internal/restore-seats/**").permitAll()
	                       // <-- allow without token
	                        .requestMatchers("/events/update-seats/**").hasRole("ADMIN")
	                        .requestMatchers("/events/restore-seats/**").hasRole("ADMIN")
	                        .requestMatchers(HttpMethod.POST, "/events/create").hasRole("ADMIN")
	                        .requestMatchers(HttpMethod.PUT, "/events/update/**").hasRole("ADMIN")
	                        .requestMatchers(HttpMethod.DELETE, "/events/delete/**").hasRole("ADMIN")
	                        .requestMatchers(HttpMethod.GET, "/events/**").permitAll()
	                        .anyRequest().authenticated()  // all other endpoints require token
	                ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


	        return http.build();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}


