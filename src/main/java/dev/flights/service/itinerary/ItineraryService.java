package dev.flights.service.itinerary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import dev.flights.service.itinerary.ShortestPath.Graph;
import dev.flights.service.itinerary.ShortestPath.Node;
import dev.flights.service.itinerary.ShortestPath.Edge;
import dev.flights.service.itinerary.ShortestPath.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import dev.flights.service.itinerary.ShortestPath.ShortestPathContract;
import dev.flights.entity.flight.Flight;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItineraryService {
    @Autowired
    @Qualifier("dijkstraShortestPathAlgorithm")
    @Getter
    @NonNull
    private ShortestPathContract djp;

    @Getter
    private ItinerarySeed seed = new ItinerarySeed();

    @Getter
    @Setter
    private ItineraryOptions options = new ItineraryOptions();

    public void setSeed(ItinerarySeed seed) {
        this.seed = seed;

    }
    
    public List<Flight> getResult() {
        djp.setGraph(this.seed.getGraph());
        djp.setStartNode(this.seed.getGraph().getNodeByName(this.options.getDepartureAirport().getId().toString()));
        djp.setEndNode(this.seed.getGraph().getNodeByName(this.options.getArrivalAirport().getId().toString()));

        djp.run();

        List<Visit> bestPath = djp.getBestPath();

        List<Flight> flights = new ArrayList<>();
        bestPath.forEach(_step -> {
            if (_step.getThroughEdge() != null) {
                flights.add(seed.getFlights().get(_step.getThroughEdge().getName()));
            }
        });

        return flights;
    }
}
