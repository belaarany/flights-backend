package dev.flights.service.itinerary;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import dev.flights.entity.airport.Airport;
import dev.flights.entity.flight.Flight;
import dev.flights.service.itinerary.ShortestPath.DijkstraShortestPath.DijkstraShortestPath;

public class ItineraryServiceIntegrationTest {

    private DijkstraShortestPath djp = new DijkstraShortestPath();
    private ItineraryService service = new ItineraryService(djp);

    @Test
    void djpIsNotNull() {
        assertThat(service.getDjp()).isNotNull();
    }

    @Test
    void canSeedFlights() {
        // given
        ItinerarySeed seed = new ItinerarySeed();

        Airport airport1 = Airport.builder().id(UUID.randomUUID()).build();
        Airport airport2 = Airport.builder().id(UUID.randomUUID()).build();

        Flight flight1 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport1).arrivalAirport(airport2).build();
        Flight flight2 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport1).arrivalAirport(airport2).build();
        Flight flight3 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport1).arrivalAirport(airport2).build();

        // when
        seed.addFlights(Arrays.asList(flight1, flight2, flight3));
        service.setSeed(seed);

        // then
        assertThat(service.getSeed()).isNotNull();

        assertThat(service.getSeed().getGraph().getIsDirected()).isEqualTo(true);

        assertThat(service.getSeed().getGraph().getNodes().size()).isEqualTo(2);

        assertThat(service.getSeed().getGraph().getEdges().size()).isEqualTo(3);
    }

    @Test
    void canSetEdgeWeightsCorrectly() {
        // https://mcdn1.teacherspayteachers.com/thumbitem/Coordinate-Grid-fonts-Customizable-points/original-486466-1.jpg

        // given
        ItinerarySeed seed = new ItinerarySeed();

        Airport airport1 = Airport.builder().id(UUID.randomUUID()).coordinateY(2.0).coordinateX(0.0).build();
        Airport airport2 = Airport.builder().id(UUID.randomUUID()).coordinateY(5.0).coordinateX(3.0).build();
        Airport airport3 = Airport.builder().id(UUID.randomUUID()).coordinateY(3.0).coordinateX(6.0).build();
        Airport airport4 = Airport.builder().id(UUID.randomUUID()).coordinateY(8.0).coordinateX(10.0).build();

        Flight flight1 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport1).arrivalAirport(airport2).build();
        Flight flight2 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport2).arrivalAirport(airport3).build();
        Flight flight3 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport3).arrivalAirport(airport4).build();

        // when
        seed.addFlights(Arrays.asList(flight1, flight2, flight3));
        service.setSeed(seed);

        // then
        assertThat(service.getSeed()).isNotNull();

        assertThat(service.getSeed().getGraph().getEdges().get(0).getWeight()).isEqualTo(4.242640687119285);
        assertThat(service.getSeed().getGraph().getEdges().get(1).getWeight()).isEqualTo(3.605551275463989);
        assertThat(service.getSeed().getGraph().getEdges().get(2).getWeight()).isEqualTo(6.4031242374328485);
    }

    @Test
    void canFindTheShortestPath() {
        // https://mcdn1.teacherspayteachers.com/thumbitem/Coordinate-Grid-fonts-Customizable-points/original-486466-1.jpg

        // given
        ItinerarySeed seed = new ItinerarySeed();
        ItineraryOptions options = new ItineraryOptions();

        Airport airport1 = Airport.builder().id(UUID.randomUUID()).coordinateY(2.0).coordinateX(0.0).build();
        Airport airport2 = Airport.builder().id(UUID.randomUUID()).coordinateY(5.0).coordinateX(3.0).build();
        Airport airport3 = Airport.builder().id(UUID.randomUUID()).coordinateY(3.0).coordinateX(6.0).build();
        Airport airport4 = Airport.builder().id(UUID.randomUUID()).coordinateY(8.0).coordinateX(10.0).build();

        Flight flight1 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport1).arrivalAirport(airport2).build();
        Flight flight2 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport1).arrivalAirport(airport3).build();
        Flight flight3 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport2).arrivalAirport(airport1).build();
        Flight flight4 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport2).arrivalAirport(airport3).build();
        Flight flight5 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport2).arrivalAirport(airport4).build();
        Flight flight6 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport3).arrivalAirport(airport1).build();
        Flight flight7 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport3).arrivalAirport(airport2).build();
        Flight flight8 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport3).arrivalAirport(airport4).build();
        Flight flight9 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport4).arrivalAirport(airport2).build();
        Flight flight10 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport4).arrivalAirport(airport3).build();

        seed.addFlights(Arrays.asList(flight1, flight2, flight3, flight4, flight5, flight6, flight7, flight8, flight9, flight10));

        options.setDepartureAirport(airport1);
        options.setArrivalAirport(airport4);

        service.setSeed(seed);
        service.setOptions(options);

        // when
        List<Flight> result = service.getResult();

        // then
        assertThat(result).isNotNull();

        assertThat(result.size()).isEqualTo(2);

        assertThat(result.get(0)).isEqualTo(flight1);
        assertThat(result.get(1)).isEqualTo(flight5);
    }

    @Test
    void canFindTheShortestPath2() {
        // https://mcdn1.teacherspayteachers.com/thumbitem/Coordinate-Grid-fonts-Customizable-points/original-486466-1.jpg

        // given
        ItinerarySeed seed = new ItinerarySeed();
        ItineraryOptions options = new ItineraryOptions();

        Airport airport1 = Airport.builder().id(UUID.randomUUID()).coordinateY(2.0).coordinateX(0.0).build();
        Airport airport2 = Airport.builder().id(UUID.randomUUID()).coordinateY(5.0).coordinateX(3.0).build();
        Airport airport3 = Airport.builder().id(UUID.randomUUID()).coordinateY(3.0).coordinateX(6.0).build();
        Airport airport4 = Airport.builder().id(UUID.randomUUID()).coordinateY(8.0).coordinateX(10.0).build();

        Flight flight1 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport1).arrivalAirport(airport2).build();
        Flight flight2 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport1).arrivalAirport(airport3).build();
        Flight flight3 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport2).arrivalAirport(airport1).build();
        Flight flight4 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport2).arrivalAirport(airport3).build();
        Flight flight5 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport2).arrivalAirport(airport4).build();
        Flight flight6 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport3).arrivalAirport(airport1).build();
        Flight flight7 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport3).arrivalAirport(airport2).build();
        Flight flight8 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport3).arrivalAirport(airport4).build();
        Flight flight9 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport4).arrivalAirport(airport2).build();
        Flight flight10 = Flight.builder().id(UUID.randomUUID()).departureAirport(airport4).arrivalAirport(airport3).build();

        seed.addFlights(Arrays.asList(flight1, flight2, flight3, flight4, flight5, flight6, flight7, flight8, flight9, flight10));

        options.setDepartureAirport(airport1);
        options.setArrivalAirport(airport4);

        service.setSeed(seed);
        service.setOptions(options);

        // when
        List<Flight> result = service.getResult();

        // then
        assertThat(result).isNotNull();

        assertThat(result.size()).isEqualTo(2);

        assertThat(result.get(0)).isEqualTo(flight1);
        assertThat(result.get(1)).isEqualTo(flight5);
    }
}
