package dev.flights.entity.flight;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public Flight createFlight(Flight flight) {
        Flight savedFlight = flightRepository.save(flight);
        return savedFlight;
    }

    public List<Flight> listFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flights;
    }
}
