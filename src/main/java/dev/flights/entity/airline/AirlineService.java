package dev.flights.entity.airline;

import java.util.List;

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
}
