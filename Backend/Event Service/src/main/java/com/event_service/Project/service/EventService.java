package com.event_service.Project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.event_service.Project.entity.Event;
import com.event_service.Project.repository.EventRepository;


	
	
	@Service
	public class EventService {


	    @Autowired
	    private EventRepository eventRepository;

	    private boolean isAdmin(String role) {
	        return role != null && role.equalsIgnoreCase("ADMIN");
	    }

	    // -----------------------
	    // CREATE EVENT
	    // -----------------------
	    public ResponseEntity<?> createEvent(Event event, String role) {

	        if (!isAdmin(role)) {
	            return ResponseEntity.status(403).body("Access Denied: ADMIN only");
	        }

	        Event savedEvent = eventRepository.save(event);
	        return ResponseEntity.ok(savedEvent);
	    }
	    
	    
	    //GET EVENT BY NAME
          public List<Event> getEventByName(String name) {
			
        	  
        	  
        	  
			return eventRepository.findByName(name);
		}
          
          //GET EVENT BY ID
          public Event getEventById(Long id) {
  			
  			return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("event not found"));
  		}


	    // -----------------------
	    // UPDATE EVENT
	    // -----------------------
	    public ResponseEntity<?> updateEvent(Long id, Event eventDetails, String role) {

	        if (!isAdmin(role)) {
	            return ResponseEntity.status(403).body("Access Denied: ADMIN only");
	        }

	        Event event = eventRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Event not found"));

	        event.setName(eventDetails.getName());
	        event.setDescription(eventDetails.getDescription());
	        event.setEventDate(eventDetails.getEventDate());
	        event.setPrice(eventDetails.getPrice());
	        event.setTotalSeats(eventDetails.getTotalSeats());

	        eventRepository.save(event);

	        return ResponseEntity.ok(event);
	    }


	    // -----------------------
	    // DELETE EVENT
	    // -----------------------
	    public ResponseEntity<?> deleteEvent(Long id, String role) {

	        if (!isAdmin(role)) {
	            return ResponseEntity.status(403).body("Access Denied: ADMIN only");
	        }

	        eventRepository.deleteById(id);
	        return ResponseEntity.ok("Event deleted successfully");
	    }

		public List<Event> searchEventByName(String name) {
			
			return eventRepository.findByNameContainingIgnoreCase(name);
		}

		 //UPDATE SEATS AFTER BOOKING//
		
		public void updateSeats(Long eventId, Integer totalSeats) {
			
		
			Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
			
			if (totalSeats < 0) {
		        throw new IllegalArgumentException("Seats cannot be negative");
		    }

		    if (totalSeats > event.getTotalSeats()) {
		        throw new IllegalArgumentException("Cannot increase seats via internal update");
		    }
			
		          event.setTotalSeats(totalSeats);
			
			       eventRepository.save(event);
			
			
		}
		
		
		public void restoreSeats(Long eventId, Integer totalSeats) {

		    Event event = eventRepository.findById(eventId)
		            .orElseThrow(() -> new RuntimeException("Event not found"));

		    event.setTotalSeats(event.getTotalSeats() + totalSeats);

		    eventRepository.save(event);
		}

		
	}


