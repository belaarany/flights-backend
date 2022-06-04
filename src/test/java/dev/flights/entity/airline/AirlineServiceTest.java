package dev.flights.entity.airline;

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
public class AirlineServiceTest {
    @Mock
    private AirlineRepository repository;

    @InjectMocks
    private AirlineService service;

    // private Faker faker = new Faker();

    @Test
    void canListAirlines() {
        // given
        List<Airline> airlineEntities = IntStream.range(0, 5).mapToObj(
                i -> Airline.builder().build())
                .collect(Collectors.toList());

        // when
        when(repository.findAll()).thenReturn(airlineEntities);
        List<Airline> airlines = service.listAirlines();

        // then
        assertThat(airlines.size()).isEqualTo(5);
    }

    @Test
    void canFindAirlineById() {
        // given
        Airline airlineEntity = Airline.builder().build();

        // when
        when(repository.findById(any())).thenReturn(Optional.of(airlineEntity));
        ThrowingSupplier<Airline> fn = () -> service.getAirlineById(airlineEntity.getId());

        // then
        assertDoesNotThrow(fn);
    }

    @Test
    void cannotFindAirlineById() {
        // given
        // -

        // when
        when(repository.findById(any())).thenReturn(Optional.empty());
        Executable fn = () -> service.getAirlineById(UUID.randomUUID());

        // then
        Exception e = assertThrows(Exception.class, fn);
        assertThat(e.getMessage()).isEqualTo("Airline does not exist");
    }
}
