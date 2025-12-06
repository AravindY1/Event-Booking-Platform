

	
	package com.example.payment.controller;

	import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.validation.annotation.Validated;
	import org.springframework.web.bind.annotation.*;
	import com.example.payment.dto.*;
	import com.example.payment.entity.Payment;
	import com.example.payment.service.PaymentService;
	import jakarta.servlet.http.HttpServletRequest;

	@RestController
	@RequestMapping("/payment")
	public class PaymentController {

	    @Autowired
	    private PaymentService paymentService;

	    
	    @PostMapping("/process")
	    public ResponseEntity<PaymentResponse> processPayment(@Validated @RequestBody PaymentRequest request, HttpServletRequest http) {
	      
	    	String userEmail = (String) http.getAttribute("email");
	       
	        PaymentResponse resp = paymentService.processPayment(request, userEmail);
	        return ResponseEntity.ok(resp);
	    }
	    
	   


	    @GetMapping("/booking/{bookingId}")
	    public ResponseEntity<List<Payment>> getPaymentsByBooking(@PathVariable Long bookingId) {
	        return ResponseEntity.ok(paymentService.getPaymentsByBookingId(bookingId));
	    }

	    @GetMapping("/{paymentId}")
	    public ResponseEntity<Optional<Payment>> getPayment(@PathVariable Long paymentId) {
	        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
	    }
	}


