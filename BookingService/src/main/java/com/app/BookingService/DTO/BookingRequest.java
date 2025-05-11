package com.app.BookingService.DTO;

public class BookingRequest {
	private String passengerName;
    private int age;
    private String email;
    private String contact_no;
    private int seatsBooked; 
    private Long flightId;
	public BookingRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookingRequest(String passengerName, int age, String email, String contact_no, int seatsBooked,
			Long flightId) {
		super();
		this.passengerName = passengerName;
		this.age = age;
		this.email = email;
		this.contact_no = contact_no;
		this.seatsBooked = seatsBooked;
		this.flightId = flightId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
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
    
    

}
