package dev.flights.entity.airplane;

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
public class AirplaneServiceTest {
    @Mock
    private AirplaneRepository repository;

    @InjectMocks
    private AirplaneService service;

    // private Faker faker = new Faker();

    @Test
    void canListAirplanes() {
        // given
        List<Airplane> airplaneEntities = IntStream.range(0, 5).mapToObj(
                i -> Airplane.builder().build())
                .collect(Collectors.toList());

        // when
        when(repository.findAll()).thenReturn(airplaneEntities);
        List<Airplane> airplanes = service.listAirplanes();

        // then
        assertThat(airplanes.size()).isEqualTo(5);
    }

    @Test
    void canFindAirplaneById() {
        // given
        Airplane airplaneEntity = Airplane.builder().build();

        // when
        when(repository.findById(any())).thenReturn(Optional.of(airplaneEntity));
        ThrowingSupplier<Airplane> fn = () -> service.getAirplaneById(airplaneEntity.getId());

        // then
        assertDoesNotThrow(fn);
    }

    @Test
    void cannotFindAirplaneById() {
        // given
        // -

        // when
        when(repository.findById(any())).thenReturn(Optional.empty());
        Executable fn = () -> service.getAirplaneById(UUID.randomUUID());

        // then
        Exception e = assertThrows(Exception.class, fn);
        assertThat(e.getMessage()).isEqualTo("Airplane does not exist");
    }
}
