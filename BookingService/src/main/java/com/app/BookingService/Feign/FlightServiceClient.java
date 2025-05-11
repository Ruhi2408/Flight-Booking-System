package com.app.BookingService.Feign;

import java.time.LocalDate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.BookingService.DTO.FlightDTO;

@FeignClient(name = "flightsystem", url = "http://localhost:8081")
public interface FlightServiceClient {

	@GetMapping("/flights/{flightId}/{departureDate}")
    FlightDTO getFlightById(
            @PathVariable("flightId") Long flightId, 
            @PathVariable("departureDate") LocalDate departureDate);

    @PutMapping("/flights/update/{flightId}") 
    FlightDTO updateFlight(
            @PathVariable("flightId") Long flightId, 
            @RequestBody FlightDTO updatedFlight);
}
