package dev.flights.service.itinerary.ShortestPath.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.flights.service.itinerary.ShortestPath.Graph;
import dev.flights.service.itinerary.ShortestPath.Node;
import dev.flights.service.itinerary.ShortestPath.Edge;
import dev.flights.service.itinerary.ShortestPath.ShortestPathContract;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class DijkstraShortestPath implements ShortestPathContract {
    private List<Node> visited = new ArrayList<>();
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
        visits.get(startNode).setDistance(0);

        visitNode(startNode);

        log.debug("Visited all nodes");
        visits.forEach((node, visit) -> log
                .debug(String.format("Node: %S | Distance: %s", node.getName(), visit.getDistance())));
    }

    public List<Node> getBestPath() {
        List<Node> path = new ArrayList<Node>();
        path.addAll(getPathFromNodes(path, endNode));
        Collections.reverse(path);

        if (path.size() <= 1) {
            return new ArrayList<>();
        }

        List<String> pathStr = new ArrayList<>();
        path.forEach(node -> pathStr.add(node.getName()));
        log.debug(String.join(" --> ", pathStr));

        return path;
    }

    private List<Node> getPathFromNodes(List<Node> previousNodes, Node currentNode) {
        List<Node> path = new ArrayList<>(previousNodes);
        path.add(currentNode);

        Node fromNode = visits.get(currentNode).getFromNode();

        if (fromNode == null) {
            return path;
        }

        return getPathFromNodes(path, fromNode);
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

        exploreNeighbors(node, graph.getNodeEdges(node));

        visited.add(node);

        List<Edge> edges = graph.getNodeEdges(node);
        Collections.sort(edges, new Comparator<Edge>() {
            public int compare(Edge l, Edge r) {
                return visits.get(l.getTargetNode()).getDistance() - visits.get(r.getTargetNode()).getDistance();
            }
        });
        edges.forEach(edge -> visitNode(edge.getTargetNode()));

        return;
    }

    private void exploreNeighbors(Node node, List<Edge> edges) {
        log.debug(String.format("Exploring neighbors for node %s", node.getName()));

        Integer fromTownDist = visits.get(node).getDistance();

        edges.forEach(edge -> exploreNeighbor(node, edge.getTargetNode(), edge.getWeight(), fromTownDist));
    }

    private void exploreNeighbor(Node fromNode, Node node, Integer neighborDistance, Integer fromTownDist) {
        if (fromNode.equals(node)) {
            return;
        }

        log.debug(String.format("Exploring neighbor %s", node.getName()));

        Integer currTownDist = visits.get(node).getDistance();
        Integer nextTownDist = fromTownDist + neighborDistance;

        if (currTownDist == -1 || nextTownDist < currTownDist) {
            visits.get(node).setDistance(nextTownDist);
            visits.get(node).setFromNode(fromNode);
        }
    }
}
