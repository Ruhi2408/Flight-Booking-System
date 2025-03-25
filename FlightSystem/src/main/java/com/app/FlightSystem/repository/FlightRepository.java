package com.app.FlightSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.FlightSystem.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findBySourceLocationAndDestinationAndDepartureDate(
        String sourceLocation, String destination, LocalDate departureDate);

    Flight findByIdAndDepartureDate(Long id, LocalDate departureDate);
}
