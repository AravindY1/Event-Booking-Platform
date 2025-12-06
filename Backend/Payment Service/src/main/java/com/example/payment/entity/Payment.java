package com.example.payment.entity;


	


	import java.time.LocalDateTime;
	import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

	@Entity
	@Table(name = "payments")
	public class Payment {

		
		
		
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long paymentId;

	    @NotNull
	    private Long bookingId;
	    
	    
	    private String userEmail;

	    @NotNull
	    private Double amount;

	    @Enumerated(EnumType.STRING)
	    private PaymentStatus status; // SUCCESS, FAILED, PENDING

	    private String transactionRef;

	    private LocalDateTime createdAt = LocalDateTime.now();

	    public Payment() {}

	    public Payment(String userEmail, Long bookingId, Double amount, PaymentStatus status, String transactionRef) {
	    	this.userEmail = userEmail;
	        this.bookingId = bookingId;
	        this.amount = amount;
	        this.status = status;
	        this.transactionRef = transactionRef;
	    }

	 
	   

	    public Long getPaymentId() { return paymentId; }
	    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }

	    public Long getBookingId() { return bookingId; }
	    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
        
	    
	    public String getUserEmail() {
	        return userEmail;
	    }

	    public void setUserEmail(String userEmail) {
	        this.userEmail = userEmail;
	    }
	    
	    
	    public Double getAmount() { return amount; }
	    public void setAmount(Double amount) { this.amount = amount; }

	    public PaymentStatus getStatus() { return status; }
	    public void setStatus(PaymentStatus status) { this.status = status; }

	    public String getTransactionRef() { return transactionRef; }
	    public void setTransactionRef(String transactionRef) { this.transactionRef = transactionRef; }

	    public LocalDateTime getCreatedAt() { return createdAt; }
	    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

		
	}



