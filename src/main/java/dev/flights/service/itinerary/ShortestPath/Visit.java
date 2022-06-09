package dev.flights.service.itinerary.ShortestPath;

import lombok.Data;

@Data
public class Visit {
    public Integer distance = -1;
    public Node sourceNode;
    public Node targetNode;
    public Edge throughEdge;
}
