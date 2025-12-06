package com.event.project.security;

	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.event.project.filter.JwtFilter;

	@Configuration
	@EnableWebSecurity
	public class SecurityConfig {
		
		
		@Autowired
		private JwtFilter jwtFilter;
		
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http.cors().and()
                    .csrf(csrf -> csrf.disable())   // disable CSRF for APIs
	                .authorizeHttpRequests(auth -> auth
	                        .requestMatchers("/auth/register", "/auth/login", "/events/**").permitAll() 
	                        // <-- allow without token
	                        .requestMatchers("/email/send").permitAll()
	                        .requestMatchers("/auth/allUsers").hasRole("ADMIN")
	                        .requestMatchers("/admin/**").hasRole("ADMIN")
	                        .anyRequest().authenticated()  // all other endpoints require token
	                        
	                  )
	                
	                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	          

	        return http.build();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}
