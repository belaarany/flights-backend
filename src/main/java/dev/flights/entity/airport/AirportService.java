package dev.flights.entity.airport;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    public List<Airport> listAirports() {
        List<Airport> airports = airportRepository.findAll();
        return airports;
    }

    public Airport getAirportById(UUID id) throws Exception {
        Optional<Airport> airport = airportRepository.findById(id);

        if (!airport.isPresent()) {
            throw new Exception("Airport does not exist");
        }

        return airport.get();
    }
}
