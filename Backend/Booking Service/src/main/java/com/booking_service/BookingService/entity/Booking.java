package com.booking_service.BookingService.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity 
@Table(name="Bookings")
public class Booking { 
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long bookingId; 
	
	@NotNull 
	private Long eventId; 
	
	@NotBlank 
	private String userEmail; 
	
	private Integer quantity; 
	
	
	private Double totalPrice; 
	
	
	private LocalDateTime bookingDate = LocalDateTime.now(); 
	
	
	public Booking() { 
		
		super(); 
		
	}
	
	


	public Booking(Long bookingId, @NotNull Long eventId, @NotBlank String userEmail, int quantity, double totalPrice,
			LocalDateTime bookingDate) {
		super();
		this.bookingId = bookingId;
		this.eventId = eventId;
		this.userEmail = userEmail;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.bookingDate = bookingDate;
	}




	public Long getBookingId() {
		return bookingId;
	}


	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}


	public Long getEventId() {
		return eventId;
	}


	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}




	public LocalDateTime getBookingDate() {
		return bookingDate;
	}




	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}



	
	
	
}
