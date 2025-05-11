package com.app.FlightSystem.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.FlightSystem.Service.FlightService;
import com.app.FlightSystem.model.Flight;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/all")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/search/{source}/{destination}/{departureDate}")
    public ResponseEntity<List<Flight>> findFlightsBySourceAndDestination(
            @PathVariable String source,
            @PathVariable String destination,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
        
        List<Flight> flights = flightService.searchFlights(source, destination, departureDate);
        return flights.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(flights);
    }

    @GetMapping("/{flightId}/{departureDate}")
    public ResponseEntity<Flight> getFlightById(
            @PathVariable Long flightId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
        
        Flight flight = flightService.getFlightById(flightId, departureDate);
        return (flight != null) ? ResponseEntity.ok(flight) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public Flight addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    @PutMapping("/update/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long flightId, @RequestBody Flight updatedFlight) {
        Flight flight = flightService.updateFlight(flightId, updatedFlight);
        return (flight != null) ? ResponseEntity.ok(flight) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/update/{flightId}")
    public ResponseEntity<Flight> updateFlightFields(
            @PathVariable Long flightId,
            @RequestBody Map<String, Object> updates) {

        Flight updatedFlight = flightService.updateFlightFields(flightId, updates);
        return (updatedFlight != null) ? ResponseEntity.ok(updatedFlight) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{flightId}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long flightId) {
        boolean isDeleted = flightService.deleteFlight(flightId);
        return isDeleted ? ResponseEntity.ok("Flight deleted successfully.") : ResponseEntity.notFound().build();
    }
}
