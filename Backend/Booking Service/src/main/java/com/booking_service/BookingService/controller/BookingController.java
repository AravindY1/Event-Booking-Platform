package com.booking_service.BookingService.controller;


	

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking_service.BookingService.BookingRequest.CreateBookingRequest;
import com.booking_service.BookingService.entity.Booking;
import com.booking_service.BookingService.service.BookingService;
import com.booking_service.BookingService.util.JwtUtil;



@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	
@Autowired
private JwtUtil jwtUtil;
	
//create booking
@PostMapping("/create")
public ResponseEntity<?> createBooking(
        @RequestBody CreateBookingRequest request) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String email = auth.getName();

    Booking booking = bookingService.createBooking(
            request.getEventId(),
            request.getQuantity(),
            email
    );

    return ResponseEntity.ok(booking);
}


//view all bookings by user

@GetMapping("/myBookings")
public ResponseEntity<?> getBookingsByUser(@RequestHeader("Authorization") String authHeader)

{
	
	String token = authHeader.replace("Bearer ", "");
    String email = jwtUtil.extractEmail(token);
    
    return ResponseEntity.ok(bookingService.getBookingsByUser(email));
}

//view single booking

@GetMapping("/{bookingId}")
public ResponseEntity<?> getBookingById(@PathVariable Long bookingId){
	
	return ResponseEntity.ok(bookingService.getBookingById(bookingId));
}



//cancel booking

@DeleteMapping("/cancel/{bookingId}")
public ResponseEntity<?> cancelBooking(
        @PathVariable Long bookingId,
        @RequestHeader("Authorization") String authHeader) {

    
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String email = auth.getName();

    String response = bookingService.cancelBooking(bookingId, email);

    return ResponseEntity.ok(response);
}


}


