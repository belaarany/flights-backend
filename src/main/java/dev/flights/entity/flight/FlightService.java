package dev.flights.entity.flight;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    // public Airline createAirline(Airline airline) {
    //     Airline savedAirline = airlineRepository.save(airline);
    //     return savedAirline;
    // }

    public List<Flight> listFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flights;
    }
}
