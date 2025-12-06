package com.booking_service.BookingService.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking_service.BookingService.service.AdminBookingService;

@RestController
	@RequestMapping("/admin")
	public class AdminBookingController {

	    @Autowired
	    private AdminBookingService adminService;

	    @GetMapping("/totalBookings")
	    public ResponseEntity<?> getTotalBookings() {
	        long count = adminService.getTotalBookings();
	        return ResponseEntity.ok(Map.of("count", count));
	    }
	}



