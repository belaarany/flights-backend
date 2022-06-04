package dev.flights.entity.airplane;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneService {
    @Autowired
    private AirplaneRepository airplaneRepository;

    public List<Airplane> listAirplanes() {
        List<Airplane> airplanes = airplaneRepository.findAll();
        return airplanes;
    }
}
