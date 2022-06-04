package dev.flights.controller.flight;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.flights.entity.flight.Flight;
import dev.flights.entity.flight.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;
    
    @GetMapping
    public ResponseEntity<List<Flight>> listFlights() {
        List<Flight> flights = flightService.listFlights();

        return ResponseEntity.ok(flights);
    }
}
