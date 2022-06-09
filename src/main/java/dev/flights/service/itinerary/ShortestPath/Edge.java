package dev.flights.service.itinerary.ShortestPath;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Edge {
    private String name;
    @NonNull
    private Node sourceNode;
    @NonNull
    private Node targetNode;
    @NonNull
    private Integer weight;

    public Node getOppositeNode(Node node) {
        return sourceNode.equals(node) ? targetNode : sourceNode;
    }
}
