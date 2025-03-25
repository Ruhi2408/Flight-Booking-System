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

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> findFlightsBySourceAndDestination(
            @RequestParam(required = false) String sourceLocation,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
        
        List<Flight> flights = flightService.searchFlights(sourceLocation, destination, departureDate);
        return flights.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(flights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
        
        Flight flight = flightService.getFlightById(id, departureDate);
        return (flight != null) ? ResponseEntity.ok(flight) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public Flight addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight updatedFlight) {
        Flight flight = flightService.updateFlight(id, updatedFlight);
        return (flight != null) ? ResponseEntity.ok(flight) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Flight> updateFlightFields(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        Flight updatedFlight = flightService.updateFlightFields(id, updates);
        return (updatedFlight != null) ? ResponseEntity.ok(updatedFlight) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) {
        boolean isDeleted = flightService.deleteFlight(id);
        return isDeleted ? ResponseEntity.ok("Flight deleted successfully.") : ResponseEntity.notFound().build();
    }
}
