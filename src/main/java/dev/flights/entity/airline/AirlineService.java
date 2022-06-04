package dev.flights.entity.airline;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineService {
    @Autowired
    private AirlineRepository airlineRepository;

    public List<Airline> listAirlines() {
        List<Airline> airlines = airlineRepository.findAll();
        return airlines;
    }

    public Airline getAirlineById(UUID id) throws Exception {
        Optional<Airline> airline = airlineRepository.findById(id);

        if (!airline.isPresent()) {
            throw new Exception("Airline does not exist");
        }

        return airline.get();
    }
}
