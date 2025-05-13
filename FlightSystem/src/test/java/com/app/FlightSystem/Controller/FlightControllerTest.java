package com.app.FlightSystem.Controller;

import com.app.FlightSystem.Service.FlightService;
import com.app.FlightSystem.model.Flight;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

class FlightControllerTest {

    @InjectMocks
    private FlightController flightController;

    @Mock
    private FlightService flightService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllFlights() throws Exception {
        Flight flight = new Flight(1L, "Airline1", "Source1", "Destination1", LocalDate.now(), LocalDate.now().atTime(10, 0), LocalDate.now().atTime(12, 0), 100.0, 150);
        when(flightService.getAllFlights()).thenReturn(Arrays.asList(flight));

        mockMvc.perform(MockMvcRequestBuilders.get("/flights/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L));
    }

    @Test
    void testFindFlightsBySourceAndDestination() throws Exception {
        Flight flight = new Flight(1L, "Airline1", "Source1", "Destination1", LocalDate.now(), LocalDate.now().atTime(10, 0), LocalDate.now().atTime(12, 0), 100.0, 150);
        when(flightService.searchFlights("Source1", "Destination1", LocalDate.now())).thenReturn(Arrays.asList(flight));

        mockMvc.perform(MockMvcRequestBuilders.get("/flights/search/Source1/Destination1/2025-05-12"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L));
    }

    @Test
    void testGetFlightById() throws Exception {
        Flight flight = new Flight(1L, "Airline1", "Source1", "Destination1", LocalDate.now(), LocalDate.now().atTime(10, 0), LocalDate.now().atTime(12, 0), 100.0, 150);
        when(flightService.getFlightById(1L, LocalDate.now())).thenReturn(flight);

        mockMvc.perform(MockMvcRequestBuilders.get("/flights/1/2025-05-12"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    void testAddFlight() throws Exception {
        Flight flight = new Flight(1L, "Airline1", "Source1", "Destination1", LocalDate.now(), LocalDate.now().atTime(10, 0), LocalDate.now().atTime(12, 0), 100.0, 150);
        when(flightService.addFlight(Mockito.any(Flight.class))).thenReturn(flight);

        mockMvc.perform(MockMvcRequestBuilders.post("/flights/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(flight)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    void testUpdateFlight() throws Exception {
        Flight flight = new Flight(1L, "Airline1", "Source1", "Destination1", LocalDate.now(), LocalDate.now().atTime(10, 0), LocalDate.now().atTime(12, 0), 100.0, 150);
        when(flightService.updateFlight(1L, flight)).thenReturn(flight);

        mockMvc.perform(MockMvcRequestBuilders.put("/flights/update/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(flight)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    void testDeleteFlight() throws Exception {
        when(flightService.deleteFlight(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/flights/delete/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
