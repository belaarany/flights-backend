package dev.flights.controller.flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.flights.entity.flight.Flight;
import dev.flights.entity.flight.FlightService;
import dev.flights.entity.airplane.AirplaneService;
import dev.flights.entity.airplane.Airplane;
import dev.flights.entity.airline.AirlineService;
import dev.flights.entity.airline.Airline;
import dev.flights.entity.airport.Airport;
import dev.flights.entity.airport.AirportService;
import dev.flights.controller.flight.dto.CreateFlightDTO;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private AirportService airportService;

    @GetMapping
    public ResponseEntity<List<Flight>> listFlights(
            @RequestParam("departure_airport_id") Optional<UUID> departureAirportId,
            @RequestParam("arrival_airport_id") Optional<UUID> arrivalAirportId,
            @RequestParam("departure_at") Optional<LocalDateTime> departureAt
            ) {
        List<Flight> flights = flightService.listFlights();

        return ResponseEntity.ok(flights);
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@Valid @RequestBody CreateFlightDTO body) throws Exception {
        Airplane airplane = airplaneService.getAirplaneById(body.getAirplaneId());
        Airline airline = airlineService.getAirlineById(body.getAirlineId());
        Airport departureAirport = airportService.getAirportById(body.getDepartureAirportId());
        Airport arrivalAirport = airportService.getAirportById(body.getArrivalAirportId());

        Flight flightEntity = Flight.builder()
                .departureAt(body.getDepartureAt())
                .arrivalAt(body.getArrivalAt())
                .airplane(airplane)
                .airline(airline)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .build();

        Flight flight = flightService.createFlight(flightEntity);

        return ResponseEntity.ok(flight);
    }
}
