package dev.flights.service.itinerary.ShortestPath;

import lombok.Data;

@Data
public class Visit {
    public Double distance = -1.0;
    public Node sourceNode;
    public Node targetNode;
    public Edge throughEdge;
}
