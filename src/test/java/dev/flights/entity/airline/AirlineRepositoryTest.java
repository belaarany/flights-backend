package dev.flights.entity.airline;

import java.util.List;

import dev.flights.entity.airline.AirlineRepository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AirlineRepositoryTest {
    @Autowired
    private AirlineRepository airlineRepository;    

    @Test
    @Disabled
    void something() {
        // Airline a = airlineRepository.save(new Airline("hi"));

        // List<Airline> as = airlineRepository.findAll();
        return;
    }
}
