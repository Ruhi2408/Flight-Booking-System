package com.app.BookingService.DTO;

import java.math.BigDecimal;

public class PaymentResponse {
	private String transactionId;
    private String status;
    private double amount;
    private Long bookingId;
	public PaymentResponse(String transactionId, String status, double amount, Long bookingId) {
		super();
		this.transactionId = transactionId;
		this.status = status;
		this.amount = amount;
		this.bookingId = bookingId;
	}
	public PaymentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
    
    

}
