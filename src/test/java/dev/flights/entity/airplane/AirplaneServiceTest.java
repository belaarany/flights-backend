package dev.flights.entity.airplane;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AirplaneServiceTest {
    @Mock
    private AirplaneRepository repository;

    @InjectMocks
    private AirplaneService service;

    // private Faker faker = new Faker();

    @Test
    void canListAirplanes() {
        // given
        List<Airplane> flightEntities = IntStream.range(0, 5).mapToObj(
                i -> Airplane.builder().build())
                .collect(Collectors.toList());

        // when
        when(repository.findAll()).thenReturn(flightEntities);
        List<Airplane> airlines = repository.findAll();

        // then
        assertThat(airlines.size()).isEqualTo(5);
    }
}
