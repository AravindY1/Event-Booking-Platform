package com.booking_service.BookingService.feignClient;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.booking_service.BookingService.dto.EventDTO;



@FeignClient(name = "event-service", url = "http://localhost:8081")
public interface EventClient {

    @GetMapping("/events/id/{eventId}")
    EventDTO getEventById(@PathVariable Long eventId);
    
    
    @PutMapping("/events/internal/update-seats/{eventId}")
    void  internalUpdateSeats(@PathVariable Long eventId, @RequestParam Integer totalSeats);
    
    
    
    @PutMapping("/events/internal/restore-seats/{eventId}")
    void internalRestoreSeats(@PathVariable Long eventId, @RequestParam Integer totalSeats);

    
    
}
