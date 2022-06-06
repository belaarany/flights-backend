package dev.flights.service.itinerary.ShortestPath;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge {
    private Node sourceNode;
    private Node targetNode;
    private Integer weight;
}
