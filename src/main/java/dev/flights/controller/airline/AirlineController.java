package dev.flights.controller.airline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.flights.entity.airline.Airline;
import dev.flights.entity.airline.AirlineService;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @GetMapping
    public ResponseEntity<List<Airline>> listAirlines() {
        List<Airline> airlines = airlineService.listAirlines();

        return ResponseEntity.ok(airlines);
    }
}
