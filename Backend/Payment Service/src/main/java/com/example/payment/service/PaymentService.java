
	
	package com.example.payment.service;

	import java.util.List;
import java.util.Optional;
import java.util.UUID;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.payment.dto.*;
import com.example.payment.emaildto.EmailRequestDTO;
import com.example.payment.entity.*;
	import com.example.payment.repository.PaymentRepository;
	import jakarta.transaction.Transactional;

	@Service
	public class PaymentService {

	    @Autowired
	    private PaymentRepository paymentRepository;
	    
	    
	    @Autowired
	    private RestTemplate restTemplate;

	    private final String EMAIL_URL = "http://localhost:8084/email/send";

	   
	    //  if amount is divisible by 2 => success else fail 
	    private boolean simulatePayment(Double amount) {
	     
	        return amount % 2 == 0;
	    }

	    @Transactional
	    public PaymentResponse processPayment(PaymentRequest request, String userEmail) {

	        Payment payment = new Payment();
	        payment.setBookingId(request.getBookingId());
	        payment.setUserEmail(userEmail);
	        payment.setAmount(request.getAmount());
	        payment.setStatus(PaymentStatus.PENDING);
	        payment = paymentRepository.save(payment);

	        
	        boolean ok = simulatePayment(request.getAmount());
	        payment.setStatus(ok ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);
	        payment.setTransactionRef(UUID.randomUUID().toString());
	        
	        
	        sendPaymentSuccessEmail(userEmail , payment);

	        payment = paymentRepository.save(payment);

	        return new PaymentResponse(userEmail, payment.getPaymentId(), payment.getBookingId(), payment.getAmount(), payment.getStatus(), payment.getTransactionRef());
	    }
	    
	    
	    private void sendPaymentSuccessEmail(String userEmail, Payment payment) {
	        EmailRequestDTO email = new EmailRequestDTO();
	        email.setTo(userEmail);
	        email.setSubject("Payment Successful");
	        email.setBody("Your payment of " + payment.getAmount() + 
	                      " for booking ID " + payment.getBookingId() + " was successful.");

	        restTemplate.postForObject(EMAIL_URL, email, String.class);
	    }
	    
	    

	    public List<Payment> getPaymentsByBookingId(Long bookingId) {
	        return paymentRepository.findByBookingId(bookingId);
	    }

	    public Optional<Payment> getPaymentById(Long paymentId) {
	        return Optional.of(paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found")));
	    }
	}



