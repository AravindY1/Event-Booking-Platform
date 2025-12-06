package com.event_service.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.event_service.Project.entity.Event;
import com.event_service.Project.repository.EventRepository;
import com.event_service.Project.service.EventService;
import com.event_service.Project.util.JwtUtil;


	
	
	@RestController
	@RequestMapping("/events")
	public class EventController {

	    @Autowired
	    private EventService eventService;
	    
	    @Autowired
	    private JwtUtil jwtUtil;
	    
	    
	    @Autowired
	    private EventRepository eventRepository;
	    

	    @PostMapping("/create")
	    public ResponseEntity<?> createEvent(
	            @RequestBody Event event,
	            @RequestAttribute String role) {

	    	return eventService.createEvent(event, role);
	    	
	    }
	    
	    @GetMapping("/name/{name}")
	    public ResponseEntity<?> getEventDetailsByName(@PathVariable String name){
	    	
	    	return ResponseEntity.ok(eventService.getEventByName(name));
	    }
	    
	    @GetMapping("/id/{id}")
	    public ResponseEntity<?> getEventDetailsById(@PathVariable Long id){
	    	
	    	return ResponseEntity.ok(eventService.getEventById(id));
	    }
	    
	    @GetMapping("/all")
	    public ResponseEntity<?> getAllEvents() {
	        return ResponseEntity.ok(eventRepository.findAll());
	    }

	    
	    @PutMapping("/internal/update-seats/{eventId}")
	    public ResponseEntity<String> internalUpdateSeats(
	            @PathVariable Long eventId,
	            @RequestParam Integer totalSeats) {

	        eventService.updateSeats(eventId, totalSeats);
	        return ResponseEntity.ok("Seats updated internally");
	    }
	    
	    @PutMapping("/update-seats/{eventId}")
	    public ResponseEntity<?> updateSeatsAdmin(
	            @PathVariable Long eventId,
	            @RequestParam Integer totalSeats,
	            @RequestHeader("Authorization") String authHeader) {

	        String token = authHeader.substring(7);
	        String role = jwtUtil.extractRole(token);

	        if (!role.equalsIgnoreCase("ADMIN")) {
	            return ResponseEntity.status(403).body("Only ADMIN can update seats");
	        }

	        eventService.updateSeats(eventId, totalSeats);

	        return ResponseEntity.ok("Seats updated successfully");
	    }


	    
	    
	    @GetMapping("/search")
	    public ResponseEntity<?> searchEvent(@RequestParam String name) {
	        return ResponseEntity.ok(eventService.searchEventByName(name));
	    }

	    @PutMapping("/update/{id}")
	    public ResponseEntity<?> updateEvent(
	            @PathVariable Long id,
	            @RequestBody Event event,
	            @RequestAttribute String role) 
	    {
	    	return eventService.updateEvent(id, event, role);
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> deleteEvent(
	            @PathVariable Long id,
	            @RequestAttribute String role) {


	    	return eventService.deleteEvent(id, role);
	    }
	    
	    
		@PutMapping("/internal/restore-seats/{eventId}")
		public ResponseEntity<?> restoreSeats(
		        @PathVariable Long eventId,
		        @RequestParam Integer totalSeats) {

		  
		    eventService.restoreSeats(eventId, totalSeats);
		    return ResponseEntity.ok("Seats restored successfully");
		}
		
		
	    @PutMapping("/restore-seats/{eventId}")
	    public ResponseEntity<?> restoreSeatsAdmin(
	            @PathVariable Long eventId,
	            @RequestParam Integer totalSeats,
	            @RequestHeader("Authorization") String authHeader) {

	        String token = authHeader.substring(7);
	        String role = jwtUtil.extractRole(token);

	        if (!role.equalsIgnoreCase("ADMIN")) {
	            return ResponseEntity.status(403).body("Only ADMIN can update seats");
	        }

	        eventService.restoreSeats(eventId, totalSeats);

	        return ResponseEntity.ok("Seats updated successfully");
	    }
	}


