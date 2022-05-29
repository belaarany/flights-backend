package dev.flights.entity.airline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineService {
    @Autowired
    private AirlineRepository airlineRepository;

    public Airline createAirline(Airline airline) {
        Airline savedAirline = airlineRepository.save(airline);
        return savedAirline;
    }

    public List<Airline> listAirlines() {
        List<Airline> airlines = airlineRepository.findAll();
        return airlines;
    }
}
