package com.event.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.event.project.dto.LoginRequest;
import com.event.project.dto.RegisterRequest;
import com.event.project.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;

	
	



	@RestController
	@RequestMapping("/auth")
	public class AuthController {

	    @Autowired
	    private AuthService authService;

	    @PostMapping("/register")
	    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
	        return authService.register(request);
	    }

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
	        return authService.login(request);
	    }
	    
	    @GetMapping("/allUsers")
	    public ResponseEntity<?> getAllUsers(HttpServletRequest req) {
	        System.out.println("ROLE = " + req.getAttribute("role"));
	        return ResponseEntity.ok(authService.getAllUsers());
	    }


	}


