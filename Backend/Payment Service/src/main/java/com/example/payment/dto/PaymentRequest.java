package com.example.payment.dto;


import jakarta.validation.constraints.NotNull;

public class PaymentRequest {
	
	@NotNull
    private Long bookingId;

    @NotNull
    private Double amount;


    
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount;

}
	
	
}
