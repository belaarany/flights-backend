package dev.flights.controller.airport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.flights.entity.airport.Airport;
import dev.flights.entity.airport.AirportService;

@RestController
@RequestMapping("/api/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public ResponseEntity<List<Airport>> listAirlines() {
        List<Airport> airports = airportService.listAirports();

        return ResponseEntity.ok(airports);
    }
}
