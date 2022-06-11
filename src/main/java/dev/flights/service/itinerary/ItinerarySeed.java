package dev.flights.service.itinerary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import dev.flights.entity.flight.Flight;
import dev.flights.entity.airport.Airport;
import dev.flights.service.itinerary.ShortestPath.Graph;
import dev.flights.service.itinerary.ShortestPath.Node;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import dev.flights.service.itinerary.ShortestPath.Edge;

@Slf4j
public class ItinerarySeed {
    // private Map<UUID, Flight> airports = new HashMap<>();
    @Getter
    private Map<String, Flight> flights = new HashMap<>();

    @Getter
    private Graph graph = null;

    public ItinerarySeed() {
        graph = new Graph();
        graph.setIsDirected(true);
    }

    public void addFlight(Flight flight) {
        this._addFlight(flight);
    }

    public void addFlights(List<Flight> flights) {
        flights.forEach(_flight -> this._addFlight(_flight));
    }

    private void _addFlight(Flight flight) {
        this.flights.put(flight.getId().toString(), flight);
        
        Node sourceNode = getOrAddNodeFromAirport(flight.getDepartureAirport());
        Node targetNode = getOrAddNodeFromAirport(flight.getArrivalAirport());

        Double edgeWeight = getDistanceBetweenTwoPoints(
                flight.getDepartureAirport().getCoordinateX(),
                flight.getDepartureAirport().getCoordinateY(),
                flight.getArrivalAirport().getCoordinateX(),
                flight.getArrivalAirport().getCoordinateY());

        Edge edge = Edge.builder()
                .name(flight.getId().toString())
                .sourceNode(sourceNode)
                .targetNode(targetNode)
                .weight(edgeWeight)
                .build();

        graph.addEdge(edge);
    }

    private Node getOrAddNodeFromAirport(Airport airport) {
        Node node = graph.getNodeByName(airport.getId().toString());

        if (node == null) {
            node = Node.builder()
                    .name(airport.getId().toString())
                    .build();

            graph.addNode(node);
        }

        return node;
    }

    private Double getDistanceBetweenTwoPoints(Double x1, Double y1, Double x2, Double y2) {
        try {
            Double d = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
            return d;
        } catch (Exception e) {
            return 0.0;
        }
    }
}
