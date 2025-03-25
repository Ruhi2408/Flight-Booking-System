package com.app.FlightSystem.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.FlightSystem.model.Flight;
import com.app.FlightSystem.repository.FlightRepository;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> searchFlights(String sourceLocation, String destination, LocalDate departureDate) {
        return flightRepository.findBySourceLocationAndDestinationAndDepartureDate(sourceLocation, destination, departureDate);
    }

    public Flight getFlightById(Long id, LocalDate departureDate) {
        return flightRepository.findByIdAndDepartureDate(id, departureDate);
    }

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight updateFlight(Long id, Flight updatedFlight) {
        Optional<Flight> existingFlightOpt = flightRepository.findById(id);

        if (existingFlightOpt.isPresent()) {
            Flight existingFlight = existingFlightOpt.get();
            existingFlight.setAirlines(updatedFlight.getAirlines());
            existingFlight.setSourceLocation(updatedFlight.getSourceLocation());
            existingFlight.setDestination(updatedFlight.getDestination());
            existingFlight.setDepartureDate(updatedFlight.getDepartureDate());
            existingFlight.setDepartureTime(updatedFlight.getDepartureTime());
            existingFlight.setArrivalTime(updatedFlight.getArrivalTime());
            existingFlight.setPrice(updatedFlight.getPrice());
            existingFlight.setTotalSeats(updatedFlight.getTotalSeats());

            return flightRepository.save(existingFlight);
        }
        return null;
    }

    public Flight updateFlightFields(Long id, Map<String, Object> updates) {
        Optional<Flight> existingFlightOpt = flightRepository.findById(id);

        if (existingFlightOpt.isPresent()) {
            Flight existingFlight = existingFlightOpt.get();

            if (updates.containsKey("airlines")) {
                existingFlight.setAirlines((String) updates.get("airlines"));
            }
            if (updates.containsKey("sourceLocation")) {
                existingFlight.setSourceLocation((String) updates.get("sourceLocation"));
            }
            if (updates.containsKey("destination")) {
                existingFlight.setDestination((String) updates.get("destination"));
            }
            if (updates.containsKey("departureDate")) {
                existingFlight.setDepartureDate(LocalDate.parse(updates.get("departureDate").toString()));
            }
            if (updates.containsKey("departureTime")) {
                existingFlight.setDepartureTime(LocalTime.parse(updates.get("departureTime").toString()));
            }
            if (updates.containsKey("arrivalTime")) {
                existingFlight.setArrivalTime(LocalTime.parse(updates.get("arrivalTime").toString()));
            }
            if (updates.containsKey("price")) {
                existingFlight.setPrice(Double.parseDouble(updates.get("price").toString()));
            }
            if (updates.containsKey("totalSeats")) {
                existingFlight.setTotalSeats(Integer.parseInt(updates.get("totalSeats").toString()));
            }

            return flightRepository.save(existingFlight);
        }
        return null;
    }

    public boolean deleteFlight(Long id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
