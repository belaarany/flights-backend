package dev.flights.entity.airport;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AirportServiceTest {
    @Mock
    private AirportRepository repository;

    @InjectMocks
    private AirportService service;

    // private Faker faker = new Faker();

    @Test
    void canListAirports() {
        // given
        List<Airport> airportEntities = IntStream.range(0, 5).mapToObj(
                i -> Airport.builder().build())
                .collect(Collectors.toList());

        // when
        when(repository.findAll()).thenReturn(airportEntities);
        List<Airport> airports = service.listAirports();

        // then
        assertThat(airports.size()).isEqualTo(5);
    }

    @Test
    void canFindAirportById() {
        // given
        Airport airportEntity = Airport.builder().build();

        // when
        when(repository.findById(any())).thenReturn(Optional.of(airportEntity));
        ThrowingSupplier<Airport> fn = () -> service.getAirportById(airportEntity.getId());

        // then
        assertDoesNotThrow(fn);
    }

    @Test
    void cannotFindAirportById() {
        // given
        // -

        // when
        when(repository.findById(any())).thenReturn(Optional.empty());
        Executable fn = () -> service.getAirportById(UUID.randomUUID());

        // then
        Exception e = assertThrows(Exception.class, fn);
        assertThat(e.getMessage()).isEqualTo("Airport does not exist");
    }
}
