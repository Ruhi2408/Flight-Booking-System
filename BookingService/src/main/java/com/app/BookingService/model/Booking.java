package com.app.BookingService.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;
    private int age;
    private String contact_no;
    private String email;
    private int seatsBooked;

    private Long flightId;
    

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.PENDING; // Default status

    @CreationTimestamp
    private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSeatsBooked() {
		return seatsBooked;
	}

	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Booking(Long id, String passengerName, int age, String contact_no, String email, int seatsBooked,
			Long flightId, BookingStatus status, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.passengerName = passengerName;
		this.age = age;
		this.contact_no = contact_no;
		this.email = email;
		this.seatsBooked = seatsBooked;
		this.flightId = flightId;
		this.status = status;
		this.createdAt = createdAt;
	}

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	} 
    
    
    
}
