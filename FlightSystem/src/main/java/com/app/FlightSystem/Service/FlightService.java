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

    public List<Flight> searchFlights(String source, String destination, LocalDate departureDate) {
        return flightRepository.findBySourceAndDestinationAndDepartureDate(source, destination, departureDate);
    }

    public Flight getFlightById(Long flightId, LocalDate departureDate) {
        return flightRepository.findByFlightIdAndDepartureDate(flightId, departureDate);
    }

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight updateFlight(Long flightId, Flight updatedFlight) {
        Optional<Flight> existingFlightOpt = flightRepository.findById(flightId);

        if (existingFlightOpt.isPresent()) {
            Flight existingFlight = existingFlightOpt.get();
            existingFlight.setAirlines(updatedFlight.getAirlines());
            existingFlight.setSource(updatedFlight.getSource());
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

    public Flight updateFlightFields(Long flightId, Map<String, Object> updates) {
        Optional<Flight> existingFlightOpt = flightRepository.findById(flightId);

        if (existingFlightOpt.isPresent()) {
            Flight existingFlight = existingFlightOpt.get();

            if (updates.containsKey("airlines")) {
                existingFlight.setAirlines((String) updates.get("airlines"));
            }
            if (updates.containsKey("source")) {
                existingFlight.setSource((String) updates.get("source"));
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

    public boolean deleteFlight(Long flightId) {
        if (flightRepository.existsById(flightId)) {
            flightRepository.deleteById(flightId);
            return true;
        }
        return false;
    }
}
