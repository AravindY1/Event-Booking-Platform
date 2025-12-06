package com.example.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.email.dto.EmailRequestDTO;
import com.example.email.service.EmailService;

@RestController
	@RequestMapping("/email")
	public class EmailController {

	    @Autowired
	    private EmailService emailService;

	    @PostMapping("/send")
	    public ResponseEntity<String> send(@RequestBody EmailRequestDTO req) {
	    	
	    	try {
	            emailService.sendEmail(
	            		req.getTo(),
	            		req.getSubject(),
	            		req.getBody()
	            );
	            return ResponseEntity.ok("Email sent");
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body("Email sending failed: " + e.getMessage());
	        }
	    }
	    }
	



