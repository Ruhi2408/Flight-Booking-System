package com.app.BookingService.DTO;

import java.math.BigDecimal;

public class PaymentRequest {
	private Long bookingId;
    private double amount;
    private String paymentMethod;
	public PaymentRequest(Long bookingId, double amount, String paymentMethod) {
		super();
		this.bookingId = bookingId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
	}
	public PaymentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
    
    
    
    

}
