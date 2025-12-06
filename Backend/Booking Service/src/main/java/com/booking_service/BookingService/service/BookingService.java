package com.booking_service.BookingService.service;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.booking_service.BookingService.dto.EventDTO;
import com.booking_service.BookingService.emaildto.EmailRequestDTO;
import com.booking_service.BookingService.entity.Booking;
import com.booking_service.BookingService.feignClient.EventClient;
import com.booking_service.BookingService.repository.BookingRepository;

import jakarta.transaction.Transactional;



	@Service
	public class BookingService {
		
		
		@Autowired
		private BookingRepository bookingRepository;
		
		
		@Autowired
		private EventClient eventClient;
		
		
		@Autowired
		private RestTemplate restTemplate;
		
		//create booking
		@Transactional
		public Booking createBooking(Long eventId, int quantity, String userEmail) {
			
			
			EventDTO event = eventClient.getEventById(eventId);
			
			
			if(event == null) {
				throw new RuntimeException("Event not found");
			}
			
			if(event.getTotalSeats() < quantity) {
				throw new RuntimeException("Not enough seats available");
			}
			
			 int updatedSeats = event.getTotalSeats() - quantity;
				
				eventClient.internalUpdateSeats(eventId, updatedSeats);
			
			
			double totalPrice = event.getPrice() * quantity;
			
			
			Booking booking = new Booking();
			booking.setEventId(eventId);
			booking.setUserEmail(userEmail);
			booking.setQuantity(quantity);
			booking.setTotalPrice(totalPrice);
			
			//sendBookingConfirmation(booking);
			
			return bookingRepository.save(booking);
			
			
			
	}
		
		private final String EMAIL_URL = "http://localhost:8084/email/send";
		
		
		private void sendBookingConfirmation(Booking booking) {
	        EmailRequestDTO email = new EmailRequestDTO();
	        email.setTo(booking.getUserEmail());
	        email.setSubject("Your Booking is Confirmed");
	        email.setBody("Your booking for event ID "  + booking.getEventId() +
	                      " is confirmed. Quantity: " + booking.getQuantity());

	        restTemplate.postForObject(EMAIL_URL, email, String.class);
	    }
		
		//View Bookings by user
		
		public List<Booking> getBookingsByUser(String email){
			
			return bookingRepository.findByUserEmail(email);
		}
		
		
		//View a Booking
		
		public Booking getBookingById(Long id) {
			
			return bookingRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Booking not found"));
		}
		
	
		
		
		@Transactional
		public String cancelBooking(Long bookingId, String userEmail) {

		    Booking booking = bookingRepository.findById(bookingId)
		            .orElseThrow(() -> new RuntimeException("Booking not found"));

		    if (!booking.getUserEmail().equals(userEmail)) {
		        throw new RuntimeException("You are not allowed to cancel someone else’s booking");
		    }

		    // Restore seats in Event Service
		    eventClient.internalRestoreSeats(
		            booking.getEventId(),
		            booking.getQuantity()
		    );

		    bookingRepository.delete(booking);

		    return "Booking cancelled successfully";
		}
		
		

		
		
		
	}


