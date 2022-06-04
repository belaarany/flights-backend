package dev.flights.controller.airplane;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.flights.entity.airplane.Airplane;
import dev.flights.entity.airplane.AirplaneService;

@RestController
@RequestMapping("/api/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;
    
    @GetMapping
    public ResponseEntity<List<Airplane>> listAirplanes() {
        List<Airplane> airplanes = airplaneService.listAirplanes();

        return ResponseEntity.ok(airplanes);
    }
}
