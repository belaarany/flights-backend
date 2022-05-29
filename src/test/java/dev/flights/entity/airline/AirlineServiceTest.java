package dev.flights.entity.airline;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import dev.flights.entity.airline.Airline;
import dev.flights.entity.airline.AirlineRepository;
import dev.flights.entity.airline.AirlineService;
import com.github.javafaker.Faker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AirlineServiceTest {
    @Mock
    private AirlineRepository repository;

    @InjectMocks
    private AirlineService service;

    private Faker faker = new Faker();

    // @BeforeEach
    // void setUp() {
    // }

    @Test
    void canCreateAirline() {
        // given
        Airline airlineEntity = Airline.builder()
                .name(faker.company().name())
                .logoUrl(faker.company().logo())
                .build();

        // when
        when(repository.save(any(Airline.class))).thenReturn(airlineEntity);
        Airline airline = service.createAirline(airlineEntity);

        // then
        assertThat(airline).isEqualTo(airlineEntity);
    }

    @Test
    void canListAirlines() {
        // given
        List<Airline> airlineEntities = IntStream.range(0, 5).mapToObj(
                i -> Airline.builder()
                        .name(faker.company().name())
                        .logoUrl(faker.company().logo())
                        .build())
                .collect(Collectors.toList());

        // when
        when(repository.findAll()).thenReturn(airlineEntities);
        List<Airline> airlines = repository.findAll();

        // then
        assertThat(airlines.size()).isEqualTo(5);
    }
}
