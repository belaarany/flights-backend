package dev.flights.entity.flight;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.flights.entity.airline.Airline;
import dev.flights.entity.airline.AirlineRepository;
import dev.flights.entity.airplane.Airplane;
import dev.flights.entity.airplane.AirplaneRepository;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {
    @Mock
    private FlightRepository repository;

    @Mock
    private AirplaneRepository airplaneRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private FlightService service;

    private Faker faker = new Faker();

    @Test
    void canListFlights() {
        // given
        List<Flight> flightEntities = IntStream.range(0, 5).mapToObj(
                i -> Flight.builder().build())
                .collect(Collectors.toList());

        // when
        when(repository.findAll()).thenReturn(flightEntities);
        List<Flight> flights = service.listFlights();

        // then
        assertThat(flights.size()).isEqualTo(5);

        assertThat(flights.get(0)).isNotNull();
        assertThat(flights.get(0).getKind()).isEqualTo("flight");
    }

    @Test
    void canListFlightsWithRelations() {
        // given
        Airplane airplaneEntity = Airplane.builder().build();
        Airline airlineEntity = Airline.builder().build();
        Flight flightEntity = Flight.builder()
                .airplane(airplaneEntity)
                .airline(airlineEntity)
                .build();

        // when
        when(repository.findAll()).thenReturn(Arrays.asList(flightEntity));
        List<Flight> flights = service.listFlights();

        // then
        assertThat(flights.size()).isEqualTo(1);

        assertThat(flights.get(0)).isNotNull();
        assertThat(flights.get(0).getKind()).isEqualTo("flight");

        assertThat(flights.get(0).getAirplane()).isNotNull();
        assertThat(flights.get(0).getAirplane()).isEqualTo(airplaneEntity);

        assertThat(flights.get(0).getAirline()).isNotNull();
        assertThat(flights.get(0).getAirline()).isEqualTo(airlineEntity);
    }

    @Test
    void canCreateFlight() {
        // given
        Flight flightEntity = Flight.builder().build();

        // when
        when(repository.save(any(Flight.class))).thenReturn(flightEntity);
        Flight flight = service.createFlight(flightEntity);

        // then
        assertThat(flight).isEqualTo(flightEntity);
    }

    @Test
    void canCreateFlightWithRelations() {
        // given
        Airplane airplaneEntity = Airplane.builder().build();
        Airline airlineEntity = Airline.builder().build();
        Flight flightEntity = Flight.builder()
                .airplane(airplaneEntity)
                .airline(airlineEntity)
                .build();

        // when
        when(repository.save(any(Flight.class))).thenReturn(flightEntity);
        Flight flight = service.createFlight(flightEntity);

        // then
        assertThat(flight).isNotNull();
        assertThat(flight).isEqualTo(flightEntity);

        assertThat(flight.getKind()).isEqualTo("flight");

        assertThat(flight.getAirplane()).isNotNull();
        assertThat(flight.getAirplane()).isEqualTo(airplaneEntity);

        assertThat(flight.getAirline()).isNotNull();
        assertThat(flight.getAirline()).isEqualTo(airlineEntity);
    }
}
