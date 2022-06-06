package dev.flights.service.itinerary.ShortestPath.DijkstraShortestPath;

import dev.flights.service.itinerary.ShortestPath.Node;
import lombok.Data;

@Data
public class Visit {
    public Integer distance = -1;
    public Node fromNode;
}
