package com.event_service.Project.entity;




	import java.time.LocalDateTime;

	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;
	import jakarta.validation.constraints.NotBlank;
	import jakarta.validation.constraints.NotNull;

	@Entity
	@Table(name = "events")
	public class Event {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long eventId;

	    @NotBlank
	    private String name;

	    @NotBlank
	    private String description;

	    @NotNull
	    private LocalDateTime eventDate;

	    @NotNull
	    private Double price;

	    @NotNull
	    private Integer totalSeats;

		public Long getEventId() {
			return eventId;
		}

		public void setEventId(Long eventId) {
			this.eventId = eventId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}



		public LocalDateTime getEventDate() {
			return eventDate;
		}

		public void setEventDate(LocalDateTime eventDate) {
			this.eventDate = eventDate;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public Integer getTotalSeats() {
			return totalSeats;
		}

		public void setTotalSeats(Integer totalSeats) {
			this.totalSeats = totalSeats;
		}

	    
	}
		
		





