package com.flights.controller.airline;

import javax.validation.Valid;

import com.flights.controller.airline.AirlineRequests.CreateAirlineRequest;
import com.flights.entity.airline.Airline;
import com.flights.entity.airline.AirlineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @PostMapping
    public ResponseEntity<Airline> createAirline(@Valid @RequestBody CreateAirlineRequest body) {
        Airline airlineEntity = Airline.builder()
                .name(body.getName())
                .logoUrl(body.getLogoUrl())
                .build();

        Airline airline = airlineService.createAirline(airlineEntity);

        return ResponseEntity.ok(airline);
    }
}
