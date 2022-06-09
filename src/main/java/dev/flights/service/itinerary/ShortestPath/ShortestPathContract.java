package dev.flights.service.itinerary.ShortestPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ShortestPathContract {
    public Graph graph = new Graph();
    public Node startNode = new Node();
    public Node endNode = new Node();
    public Map<Node, Visit> visits = new HashMap<>();

    public void setGraph(Graph graph);

    public void setStartNode(Node node);

    public void setEndNode(Node node);

    public void run();

    public List<Visit> getBestPath();
}
