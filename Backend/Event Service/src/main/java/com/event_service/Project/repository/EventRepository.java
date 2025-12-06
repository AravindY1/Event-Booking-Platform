package com.event_service.Project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event_service.Project.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	  List<Event> findByNameContainingIgnoreCase(String name);
	  
	  List<Event> findByName(String name);
	}

