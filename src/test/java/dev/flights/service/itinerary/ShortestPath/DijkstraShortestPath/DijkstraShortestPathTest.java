package dev.flights.service.itinerary.ShortestPath.DijkstraShortestPath;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import dev.flights.service.itinerary.ShortestPath.Graph;
import dev.flights.service.itinerary.ShortestPath.Node;
import dev.flights.service.itinerary.ShortestPath.Edge;
import dev.flights.service.itinerary.ShortestPath.ShortestPathContract;

public class DijkstraShortestPathTest {
    @Test
    void firstTest() {
        // https://www.baeldung.com/java-dijkstra#:~:text=Java%20Implementation&text=As%20for%20the%20shortestPath%20attribute,described%20in%20the%20initialization%20step.
        
        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        graph.addEdge(new Edge(nodeA, nodeB, 10));
        graph.addEdge(new Edge(nodeA, nodeC, 15));

        graph.addEdge(new Edge(nodeB, nodeF, 15));
        graph.addEdge(new Edge(nodeB, nodeD, 12));

        graph.addEdge(new Edge(nodeC, nodeE, 10));

        graph.addEdge(new Edge(nodeD, nodeF, 1));
        graph.addEdge(new Edge(nodeD, nodeE, 2));

        graph.addEdge(new Edge(nodeF, nodeE, 5));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeE);
        djp.run();
        List<Node> bestPath = djp.getBestPath();

        //then
        assertThat(bestPath.size()).isEqualTo(4);

        assertThat(bestPath.get(0)).isEqualTo(nodeA);
        assertThat(bestPath.get(1)).isEqualTo(nodeB);
        assertThat(bestPath.get(2)).isEqualTo(nodeD);
        assertThat(bestPath.get(3)).isEqualTo(nodeE);
    }

    @Test
    void secondTest() {
        // https://www.techiedelight.com/single-source-shortest-paths-dijkstras-algorithm/
        
        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");

        graph.addEdge(new Edge(nodeA, nodeB, 10));
        graph.addEdge(new Edge(nodeA, nodeE, 3));

        graph.addEdge(new Edge(nodeB, nodeE, 4));
        graph.addEdge(new Edge(nodeB, nodeC, 2));

        graph.addEdge(new Edge(nodeC, nodeD, 9));

        graph.addEdge(new Edge(nodeD, nodeC, 7));

        graph.addEdge(new Edge(nodeE, nodeB, 1));
        graph.addEdge(new Edge(nodeE, nodeC, 8));
        graph.addEdge(new Edge(nodeE, nodeD, 2));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeC);
        djp.run();
        List<Node> bestPath = djp.getBestPath();

        //then
        assertThat(bestPath.size()).isEqualTo(4);

        assertThat(bestPath.get(0)).isEqualTo(nodeA);
        assertThat(bestPath.get(1)).isEqualTo(nodeE);
        assertThat(bestPath.get(2)).isEqualTo(nodeB);
        assertThat(bestPath.get(3)).isEqualTo(nodeC);
    }

    @Test
    void thirdTest() {
        // https://www.techiedelight.com/single-source-shortest-paths-dijkstras-algorithm/
        
        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");

        graph.addEdge(new Edge(nodeA, nodeB, 10));
        graph.addEdge(new Edge(nodeA, nodeE, 3));

        graph.addEdge(new Edge(nodeB, nodeE, 4));
        graph.addEdge(new Edge(nodeB, nodeC, 2));

        graph.addEdge(new Edge(nodeC, nodeD, 9));

        graph.addEdge(new Edge(nodeD, nodeC, 7));

        graph.addEdge(new Edge(nodeE, nodeB, 1));
        graph.addEdge(new Edge(nodeE, nodeC, 8));
        graph.addEdge(new Edge(nodeE, nodeD, 2));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeB);
        djp.run();
        List<Node> bestPath = djp.getBestPath();

        //then
        assertThat(bestPath.size()).isEqualTo(3);

        assertThat(bestPath.get(0)).isEqualTo(nodeA);
        assertThat(bestPath.get(1)).isEqualTo(nodeE);
        assertThat(bestPath.get(2)).isEqualTo(nodeB);
    }
    
    @Test
    void canExploreInTheRightOrder() {
        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");

        graph.addEdge(new Edge(nodeA, nodeB, 2));
        graph.addEdge(new Edge(nodeA, nodeC, 1));

        graph.addEdge(new Edge(nodeB, nodeD, 1));
        graph.addEdge(new Edge(nodeC, nodeD, 1));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeD);
        djp.run();
        List<Node> bestPath = djp.getBestPath();

        //then
        assertThat(bestPath.size()).isEqualTo(3);

        assertThat(bestPath.get(0)).isEqualTo(nodeA);
        assertThat(bestPath.get(1)).isEqualTo(nodeC);
        assertThat(bestPath.get(2)).isEqualTo(nodeD);
    }

    @Test
    void canExploreInTheRightOrder2() {
        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");

        graph.addEdge(new Edge(nodeA, nodeB, 1));
        graph.addEdge(new Edge(nodeA, nodeC, 2));

        graph.addEdge(new Edge(nodeB, nodeD, 1));
        graph.addEdge(new Edge(nodeC, nodeD, 1));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeD);
        djp.run();
        List<Node> bestPath = djp.getBestPath();

        //then
        assertThat(bestPath.size()).isEqualTo(3);

        assertThat(bestPath.get(0)).isEqualTo(nodeA);
        assertThat(bestPath.get(1)).isEqualTo(nodeB);
        assertThat(bestPath.get(2)).isEqualTo(nodeD);
    }
    
    @Test
    void canReturnWhenHasOnlyOneEdge() {
        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");

        graph.addEdge(new Edge(nodeA, nodeB, 3));
        graph.addEdge(new Edge(nodeA, nodeC, 2));
        graph.addEdge(new Edge(nodeA, nodeD, 1));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeC);
        djp.run();
        List<Node> bestPath = djp.getBestPath();

        //then
        assertThat(bestPath.size()).isEqualTo(2);

        assertThat(bestPath.get(0)).isEqualTo(nodeA);
        assertThat(bestPath.get(1)).isEqualTo(nodeC);
    } 

    @Test
    void canReturnWhenHasNoPath() {
        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");

        graph.addEdge(new Edge(nodeA, nodeB, 3));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeC);
        djp.run();
        List<Node> bestPath = djp.getBestPath();

        //then
        assertThat(bestPath.size()).isEqualTo(0);
    }
}
