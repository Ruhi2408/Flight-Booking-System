package com.app.BookingService.controller;

import java.util.List;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.BookingService.DTO.BookingRequest;
import com.app.BookingService.model.Booking;
import com.app.BookingService.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    
    @PostMapping("/book")
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequest bookingRequest) {
        System.out.println("Received booking: " + bookingRequest);
        try {
            Booking bookedFlight = bookingService.bookFlight(bookingRequest);
            return ResponseEntity.ok(bookedFlight);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(e.getMessage());
        }
    }

}
