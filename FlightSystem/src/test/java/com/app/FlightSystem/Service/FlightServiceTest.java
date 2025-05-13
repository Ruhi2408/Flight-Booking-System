package com.app.FlightSystem.Service;

import com.app.FlightSystem.model.Flight;
import com.app.FlightSystem.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FlightServiceTest {

    @InjectMocks
    private FlightService flightService;

    @Mock
    private FlightRepository flightRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchFlights() {
        Flight flight = new Flight(1L, "Airline1", "Source1", "Destination1", LocalDate.now(), LocalDate.now().atTime(10, 0), LocalDate.now().atTime(12, 0), 100.0, 150);
        when(flightRepository.findBySourceAndDestinationAndDepartureDate("Source1", "Destination1", LocalDate.now())).thenReturn(java.util.List.of(flight));

        var flights = flightService.searchFlights("Source1", "Destination1", LocalDate.now());

        assertNotNull(flights);
        assertEquals(1, flights.size());
        assertEquals("Airline1", flights.get(0).getAirlines());
    }

    @Test
    void testGetFlightById() {
        Flight flight = new Flight(1L, "Airline1", "Source1", "Destination1", LocalDate.now(), LocalDate.now().atTime(10, 0), LocalDate.now().atTime(12, 0), 100.0, 150);
        when(flightRepository.findByFlightIdAndDepartureDate(1L, LocalDate.now())).thenReturn(flight);

        Flight result = flightService.getFlightById(1L, LocalDate.now());

        assertNotNull(result);
        assertEquals("Airline1", result.getAirlines());
    }

    @Test
    void testAddFlight() {
        Flight flight = new Flight(1L, "Airline1", "Source1", "Destination1", LocalDate.now(), LocalDate.now().atTime(10, 0), LocalDate.now().atTime(12, 0), 100.0, 150);
        when(flightRepository.save(Mockito.any(Flight.class))).thenReturn(flight);

        Flight addedFlight = flightService.addFlight(flight);

        assertNotNull(addedFlight);
        assertEquals("Airline1", addedFlight.getAirlines());
    }

    @Test
    void testUpdateFlight() {
        Flight flight = new Flight(1L, "Airline1", "Source1", "Destination1", LocalDate.now(), LocalDate.now().atTime(10, 0), LocalDate.now().atTime(12, 0), 100.0, 150);
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(flightRepository.save(flight)).thenReturn(flight);

        Flight updatedFlight = flightService.updateFlight(1L, flight);

        assertNotNull(updatedFlight);
        assertEquals("Airline1", updatedFlight.getAirlines());
    }

    @Test
    void testDeleteFlight() {
        when(flightRepository.existsById(1L)).thenReturn(true);

        boolean isDeleted = flightService.deleteFlight(1L);

        assertTrue(isDeleted);
        verify(flightRepository, times(1)).deleteById(1L);
    }
}
