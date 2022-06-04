package dev.flights.entity.airplane;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Airplane getAirplaneById(UUID id) throws Exception {
        Optional<Airplane> airplane = airplaneRepository.findById(id);

        if (!airplane.isPresent()) {
            throw new Exception("Airplane does not exist");
        }

        return airplane.get();
    }
}
