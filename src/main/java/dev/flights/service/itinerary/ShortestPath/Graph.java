package dev.flights.service.itinerary.ShortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class Graph {
    private List<Node> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    private Boolean isDirected = false;

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addNodes(List<Node> nodes) {
        this.nodes.addAll(nodes);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void addEdges(List<Edge> edges) {
        this.edges.addAll(edges);
    }

    public Node getNodeByName(String name) {
        List<Node> nodes = this.nodes.stream()
                .filter(_node -> _node.getName().equals(name))
                .collect(Collectors.toList());

        if (nodes.size() != 1) {
            return null;
        }

        return nodes.get(0);
    }

    public Edge getEdgeByName(String name) {
        List<Edge> edges = this.edges.stream()
                .filter(_edge -> _edge.getName().equals(name))
                .collect(Collectors.toList());

        if (edges.size() != 1) {
            return null;
        }

        return edges.get(0);
    }

    public List<Edge> getNodeEdges(Node node) {
        return edges.stream()
                .filter(_edge -> _edge.getSourceNode().equals(node) || _edge.getTargetNode().equals(node))
                .collect(Collectors.toList());
    }

    // public String toString() {

    // }
}
