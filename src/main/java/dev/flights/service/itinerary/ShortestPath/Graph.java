package dev.flights.service.itinerary.ShortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class Graph {
    private List<Node> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addNodes(List<Node> nodes) {
        this.nodes.addAll(nodes);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public List<Edge> getNodeEdges(Node node) {
        return edges.stream()
            .filter(edge -> edge.getSourceNode().equals(node) || edge.getTargetNode().equals(node))
            .collect(Collectors.toList());
    }
}
