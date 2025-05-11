package com.app.BookingService.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightDTO {
	private Long flightId;
	private String airlines;
	private String source;
	private String destination;
	private LocalDate departureDate;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private double price;
	private int totalSeats;
	public Long getFlightId() {
		return flightId;
	}
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	public String getAirlines() {
		return airlines;
	}
	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public FlightDTO(Long flightId, String airlines, String source, String destination, LocalDate departureDate,
			LocalTime departureTime, LocalTime arrivalTime, double price, int totalSeats) {
		super();
		this.flightId = flightId;
		this.airlines = airlines;
		this.source = source;
		this.destination = destination;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.price = price;
		this.totalSeats = totalSeats;
	}
	public FlightDTO() {
	}
	
	

}
