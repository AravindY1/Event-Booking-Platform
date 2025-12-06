package com.example.payment.dto;


	

	import com.example.payment.entity.PaymentStatus;

	public class PaymentResponse {
		
	
	 
		private String userEmail; 
		private Long paymentId;
	    private Long bookingId;
	    private Double amount;
	    private PaymentStatus status;
	    private String transactionRef;

	    public PaymentResponse(String userEmail, Long paymentId, Long bookingId, Double amount, PaymentStatus status, String transactionRef) {
	       
	    	this.userEmail = userEmail;
	    	this.paymentId = paymentId;
	        this.bookingId = bookingId;
	        this.amount = amount;
	        this.status = status;
	        this.transactionRef = transactionRef;
	    }
	   
	    
	    public String getUserEmail() {
			return userEmail;
		}

		public Long getPaymentId() { return paymentId; }
	     public Long getBookingId() { return bookingId; }
	    public Double getAmount() { return amount; }
	    public PaymentStatus getStatus() { return status; }
	    public String getTransactionRef() { return transactionRef; }
	}



