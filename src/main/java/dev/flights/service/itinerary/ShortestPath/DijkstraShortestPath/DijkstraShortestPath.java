package dev.flights.service.itinerary.ShortestPath.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import dev.flights.service.itinerary.ShortestPath.Graph;
import dev.flights.service.itinerary.ShortestPath.Node;
import dev.flights.service.itinerary.ShortestPath.Edge;
import dev.flights.service.itinerary.ShortestPath.ShortestPathContract;
import dev.flights.service.itinerary.ShortestPath.Visit;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class DijkstraShortestPath implements ShortestPathContract {
    private Set<Node> visited = new HashSet<>();
    private Set<Node> irrelevantNodes = new HashSet<>();

    @Getter
    private Map<Node, Visit> visits = new HashMap<>();

    @Setter
    public Graph graph;
    @Setter
    public Node startNode;
    @Setter
    public Node endNode;

    public void run() {
        log.debug("Starting");

        graph.getNodes().forEach(node -> visits.put(node, new Visit()));
        visits.get(startNode).setDistance(0.0);
        visits.get(startNode).setTargetNode(startNode);

        visitNode(startNode);

        log.debug("Visited all nodes");
        printVisits();
    }

    public List<Visit> getBestPath() {
        List<Visit> path = new ArrayList<Visit>();
        path.addAll(getPathFromVisit(path, visits.get(endNode)));
        Collections.reverse(path);

        if (path.size() <= 1) {
            return new ArrayList<>();
        }

        printPath(path);

        return path;
    }

    private List<Visit> getPathFromVisit(List<Visit> previousVisits, Visit currentVisit) {
        List<Visit> path = new ArrayList<>(previousVisits);
        path.add(currentVisit);

        Visit fromVisit = visits.get(currentVisit.getSourceNode());

        if (fromVisit == null) {
            return path;
        }

        return getPathFromVisit(path, fromVisit);
    }

    private void visitNode(Node node) {
        log.debug(String.format("Visiting node %s", node.getName()));

        if (visited.contains(node)) {
            log.debug("Node already visited");
            return;
        }

        if (!graph.getNodes().contains(node)) {
            log.debug("Node doesnt exist");
            return;
        }

        if (irrelevantNodes.contains(node)) {
            log.debug("Node is irrelevant");
            return;
        }

        exploreNeighbors(node, graph.getNodeEdges(node));

        visited.add(node);

        List<Edge> edges = graph.getNodeEdges(node);
        Collections.sort(edges, new Comparator<Edge>() {
            public int compare(Edge l, Edge r) {
                Double ld = visits.get(graph.getIsDirected() ? l.getTargetNode() : l.getOppositeNode(node)).getDistance();
                Double rd = visits.get(graph.getIsDirected() ? r.getTargetNode() : r.getOppositeNode(node)).getDistance();
                return (int) (ld - rd);
            }
        });
        edges.forEach(edge -> visitNode(graph.getIsDirected() ? edge.getTargetNode() : edge.getOppositeNode(node)));

        return;
    }

    private void exploreNeighbors(Node node, List<Edge> edges) {
        log.debug(String.format("Exploring neighbors for node %s", node.getName()));

        Double sourceNodeDistance = visits.get(node).getDistance();

        edges.forEach(edge -> exploreNeighbor(node, edge, sourceNodeDistance));
    }

    private void exploreNeighbor(Node sourceNode, Edge edge, Double sourceNodeDistance) {
        Node targetNode = graph.getIsDirected() ? edge.getTargetNode() : edge.getOppositeNode(sourceNode);
        Double edgeDistance = edge.getWeight();

        if (sourceNode.equals(targetNode)) {
            return;
        }

        log.debug(String.format("Exploring neighbor %s", targetNode.getName()));
        printVisits();

        Double currentNodeDistance = visits.get(targetNode).getDistance();
        Double nextNodeDistance = sourceNodeDistance + edgeDistance;

        if (currentNodeDistance == -1 || nextNodeDistance < currentNodeDistance) {
            if (targetNode.getMaxWeight() != null && targetNode.getMaxWeight() < nextNodeDistance) {
                irrelevantNodes.add(targetNode);
            }
            if (targetNode.getMinWeight() != null && targetNode.getMinWeight() > nextNodeDistance) {
                irrelevantNodes.add(targetNode);
            }
            
            if (!irrelevantNodes.contains(targetNode)) {
                visits.get(targetNode).setDistance(nextNodeDistance);
                visits.get(targetNode).setSourceNode(sourceNode);
                visits.get(targetNode).setTargetNode(targetNode);
                visits.get(targetNode).setThroughEdge(edge);
            }
        }

        log.debug("---");
        printVisits();
        log.debug("======");
    }

    private void printVisits() {
        visits.forEach((node, visit) -> {
            List<String> str = new ArrayList<>();
            str.add(node.getName());
            str.add(visit.getDistance().toString());
            Optional.ofNullable(visit.getSourceNode())
                .map(_visit -> _visit.getName())
                .ifPresent(nodeName ->
                    str.add(nodeName)    
                );
            log.debug(String.join(" | ", str));
        });
    }

    private void printPath(List<Visit> path) {
        List<String> pathStr = new ArrayList<>();
        path.forEach(visit -> {
            Optional.ofNullable(visit.getThroughEdge())
                .map(_edge -> _edge.getName())
                .ifPresent(_edgeName ->
                    pathStr.add(String.format("Edge %s", _edgeName))    
                );
            pathStr.add(String.format("Node %s", visit.getTargetNode().getName()));
        });
        log.debug(String.join(" --> ", pathStr));
    }
}
