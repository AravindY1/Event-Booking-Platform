package com.booking_service.BookingService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking_service.BookingService.repository.BookingRepository;

@Service
public class AdminBookingService {
	
	
	 @Autowired
	    private BookingRepository bookingRepository;

	    public long getTotalBookings() {
	        return bookingRepository.count();
	    }

}
