package com.app.BookingService.service;

import com.app.BookingService.DTO.BookingRequest;
import com.app.BookingService.DTO.FlightDTO;
import com.app.BookingService.model.Booking;
import com.app.BookingService.model.BookingStatus;
import com.app.BookingService.Feign.FlightServiceClient;
import com.app.BookingService.repository.BookingRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightServiceClient flightServiceClient;

    public BookingService(BookingRepository bookingRepository, FlightServiceClient flightServiceClient) {
        this.bookingRepository = bookingRepository;
        this.flightServiceClient = flightServiceClient;
    }

    @Transactional
    public Booking bookFlight(BookingRequest bookingRequest) {
        // Fetch flight details from FlightService
        FlightDTO flight = flightServiceClient.getFlightById(bookingRequest.getFlightId(), LocalDate.now());

        if (flight == null) {
            throw new RuntimeException("Flight not found!");
        }

        if (flight.getTotalSeats() < bookingRequest.getSeatsBooked()) {
            throw new RuntimeException("Not enough seats available!");
        }

        double totalAmount = flight.getPrice() * bookingRequest.getSeatsBooked();

        // Create new Booking
        Booking newBooking = new Booking();
        newBooking.setPassengerName(bookingRequest.getPassengerName());
        newBooking.setAge(bookingRequest.getAge());
        newBooking.setEmail(bookingRequest.getEmail());
        newBooking.setContact_no(bookingRequest.getContact_no());
        newBooking.setSeatsBooked(bookingRequest.getSeatsBooked());
        newBooking.setFlightId(bookingRequest.getFlightId());
        newBooking.setStatus(BookingStatus.PENDING);

        Booking savedBooking = bookingRepository.save(newBooking);

        // Update flight availability
        FlightDTO updatedFlight = new FlightDTO();
        updatedFlight.setFlightId(flight.getFlightId());
        updatedFlight.setAirlines(flight.getAirlines());
        updatedFlight.setSource(flight.getSource());
        updatedFlight.setDestination(flight.getDestination());
        updatedFlight.setDepartureDate(flight.getDepartureDate());
        updatedFlight.setDepartureTime(flight.getDepartureTime());
        updatedFlight.setArrivalTime(flight.getArrivalTime());
        updatedFlight.setPrice(flight.getPrice());
        updatedFlight.setTotalSeats(flight.getTotalSeats() - bookingRequest.getSeatsBooked());

        flightServiceClient.updateFlight(flight.getFlightId(), updatedFlight);

        return savedBooking;
    }
}
