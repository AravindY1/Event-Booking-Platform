package com.event.project.service;


	
	import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.event.project.dto.EmailRequestDTO;
import com.event.project.dto.LoginRequest;
import com.event.project.dto.RegisterRequest;
import com.event.project.entity.User;
import com.event.project.repository.UserRepository;
import com.event.project.util.JwtUtil;

	@Service
	public class AuthService {

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder encoder;

	    @Autowired
	    private JwtUtil jwtUtil;
	    
	 
	    @Autowired
	    private RestTemplate restTemplate;
	    
	   
	   

	    public ResponseEntity<?> register(RegisterRequest request) {

	        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
	        	return ResponseEntity.status(400).body("Email already registered");
	        }

	        User user = new User(
	                request.getFullName(),
	                request.getEmail(),
	                encoder.encode(request.getPassword()),
	                request.getRole()
	        );

	        User saved =  userRepository.save(user);
	        
	        sendWelcomeEmail(saved);


	  
	        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

	        return  ResponseEntity.ok("User registered successfully with token : " + token);
	    }
	    
	    
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }
	    
	  

	    private final String EMAIL_URL = "http://localhost:8084/email/send";

	    private void sendWelcomeEmail(User user) {
	        EmailRequestDTO email = new EmailRequestDTO();
	        email.setTo(user.getEmail());
	        email.setSubject("Welcome to Event Management!");
	        email.setBody("Hello " + user.getFullName() + ", your account has been created.");

	        restTemplate.postForObject(EMAIL_URL, email, String.class);
	    }

	    
	   

	    
	    


	    public ResponseEntity<?> login(LoginRequest request) {

	        User user = userRepository.findByEmail(request.getEmail())
	                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

	        if (!encoder.matches(request.getPassword(), user.getPassword())) {
	            throw new RuntimeException("Invalid email or password");
	        }

	        // Generate JWT
	        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

	        // Build JSON response
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "user login successful");
	        response.put("token", token);
	        response.put("email", user.getEmail());
	        response.put("role", user.getRole());
	        response.put("fullName", user.getFullName());

	        return ResponseEntity.ok(response);
	    }








		
	}

	
	
	
