package dev.flights.service.itinerary.ShortestPath.DijkstraShortestPath;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import dev.flights.service.itinerary.ShortestPath.Graph;
import dev.flights.service.itinerary.ShortestPath.Node;
import dev.flights.service.itinerary.ShortestPath.Edge;
import dev.flights.service.itinerary.ShortestPath.Visit;
import dev.flights.service.itinerary.ShortestPath.ShortestPathContract;

public class DijkstraShortestPathTest {
    @Test
    void generalTest1() {
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

        graph.addEdge(new Edge(nodeA, nodeB, 10.0));
        graph.addEdge(new Edge(nodeA, nodeC, 15.0));

        graph.addEdge(new Edge(nodeB, nodeF, 15.0));
        graph.addEdge(new Edge(nodeB, nodeD, 12.0));

        graph.addEdge(new Edge(nodeC, nodeE, 10.0));

        graph.addEdge(new Edge(nodeD, nodeF, 1.0));
        graph.addEdge(new Edge(nodeD, nodeE, 2.0));

        graph.addEdge(new Edge(nodeF, nodeE, 5.0));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeE);
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(4);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(nodeA);
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(nodeB);
        assertThat(bestPath.get(2).getTargetNode()).isEqualTo(nodeD);
        assertThat(bestPath.get(3).getTargetNode()).isEqualTo(nodeE);
    }

    @Test
    void generalTest2() {
        // https://www.techiedelight.com/single-source-shortest-paths-dijkstras-algorithm/

        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");

        graph.addEdge(new Edge(nodeA, nodeB, 10.0));
        graph.addEdge(new Edge(nodeA, nodeE, 3.0));

        graph.addEdge(new Edge(nodeB, nodeE, 4.0));
        graph.addEdge(new Edge(nodeB, nodeC, 2.0));

        graph.addEdge(new Edge(nodeC, nodeD, 9.0));

        graph.addEdge(new Edge(nodeD, nodeC, 7.0));

        graph.addEdge(new Edge(nodeE, nodeB, 1.0));
        graph.addEdge(new Edge(nodeE, nodeC, 8.0));
        graph.addEdge(new Edge(nodeE, nodeD, 2.0));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeC);
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(4);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(nodeA);
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(nodeE);
        assertThat(bestPath.get(2).getTargetNode()).isEqualTo(nodeB);
        assertThat(bestPath.get(3).getTargetNode()).isEqualTo(nodeC);
    }

    @Test
    void generalTest3() {
        // https://www.techiedelight.com/single-source-shortest-paths-dijkstras-algorithm/

        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");

        graph.addEdge(new Edge(nodeA, nodeB, 10.0));
        graph.addEdge(new Edge(nodeA, nodeE, 3.0));

        graph.addEdge(new Edge(nodeB, nodeE, 4.0));
        graph.addEdge(new Edge(nodeB, nodeC, 2.0));

        graph.addEdge(new Edge(nodeC, nodeD, 9.0));

        graph.addEdge(new Edge(nodeD, nodeC, 7.0));

        graph.addEdge(new Edge(nodeE, nodeB, 1.0));
        graph.addEdge(new Edge(nodeE, nodeC, 8.0));
        graph.addEdge(new Edge(nodeE, nodeD, 2.0));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeB);
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(3);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(nodeA);
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(nodeE);
        assertThat(bestPath.get(2).getTargetNode()).isEqualTo(nodeB);
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

        graph.addEdge(new Edge(nodeA, nodeB, 2.0));
        graph.addEdge(new Edge(nodeA, nodeC, 1.0));

        graph.addEdge(new Edge(nodeB, nodeD, 1.0));
        graph.addEdge(new Edge(nodeC, nodeD, 1.0));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeD);
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(3);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(nodeA);
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(nodeC);
        assertThat(bestPath.get(2).getTargetNode()).isEqualTo(nodeD);
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

        graph.addEdge(new Edge(nodeA, nodeB, 1.0));
        graph.addEdge(new Edge(nodeA, nodeC, 2.0));

        graph.addEdge(new Edge(nodeB, nodeD, 1.0));
        graph.addEdge(new Edge(nodeC, nodeD, 1.0));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeD);
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(3);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(nodeA);
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(nodeB);
        assertThat(bestPath.get(2).getTargetNode()).isEqualTo(nodeD);
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

        graph.addEdge(new Edge(nodeA, nodeB, 3.0));
        graph.addEdge(new Edge(nodeA, nodeC, 2.0));
        graph.addEdge(new Edge(nodeA, nodeD, 1.0));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeC);
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(2);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(nodeA);
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(nodeC);
    }

    @Test
    void canReturnWhenHasNoPath() {
        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");

        graph.addEdge(new Edge(nodeA, nodeB, 3.0));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeC);
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(0);
    }

    @Test
    void canWorkWithMultipleEdges() {
        // given
        DijkstraShortestPath djp = new DijkstraShortestPath();

        Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");

        for (int i = 0; i < 5; i++) {
            graph.addEdge(new Edge(nodeA, nodeB, 50.0 + i));
            graph.addEdge(new Edge(nodeB, nodeA, 50.0 + i));
            graph.addEdge(new Edge(nodeB, nodeC, 50.0 + i));
            graph.addEdge(new Edge(nodeC, nodeB, 50.0 + i));
        }

        Edge edge1 = new Edge("1", nodeA, nodeB, 1.0);
        Edge edge2 = new Edge("2", nodeB, nodeA, 2.0);
        Edge edge3 = new Edge("3", nodeB, nodeC, 3.0);
        Edge edge4 = new Edge("4", nodeC, nodeB, 4.0);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);

        for (int i = 0; i < 5; i++) {
            graph.addEdge(new Edge(nodeA, nodeB, 500.0 + i));
            graph.addEdge(new Edge(nodeB, nodeA, 500.0 + i));
            graph.addEdge(new Edge(nodeB, nodeC, 500.0 + i));
            graph.addEdge(new Edge(nodeC, nodeB, 500.0 + i));
        }

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeC);
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(3);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(nodeA);
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(nodeB);
        assertThat(bestPath.get(2).getTargetNode()).isEqualTo(nodeC);

        assertThat(bestPath.get(0).getDistance()).isEqualTo(0);
        assertThat(bestPath.get(1).getDistance()).isEqualTo(0 + 1);
        assertThat(bestPath.get(2).getDistance()).isEqualTo(0 + 1 + 3);

        assertThat(bestPath.get(0).getThroughEdge()).isEqualTo(null);
        assertThat(bestPath.get(1).getThroughEdge()).isEqualTo(edge1);
        assertThat(bestPath.get(2).getThroughEdge()).isEqualTo(edge3);
    }

    @Test
    void canWorkWithNotDirectedGraph() {
        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();
        graph.setIsDirected(false);

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");

        graph.addEdge(new Edge(nodeA, nodeB, 2.0));
        graph.addEdge(new Edge(nodeC, nodeA, 1.0));

        graph.addEdge(new Edge(nodeB, nodeD, 1.0));
        graph.addEdge(new Edge(nodeC, nodeD, 1.0));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeD);
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(3);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(nodeA);
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(nodeC);
        assertThat(bestPath.get(2).getTargetNode()).isEqualTo(nodeD);
    }

    @Test
    void canWorkWithDirectedGraph() {
        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();
        graph.setIsDirected(true);

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");

        graph.addEdge(new Edge(nodeA, nodeB, 2.0));
        graph.addEdge(new Edge(nodeC, nodeA, 1.0));

        graph.addEdge(new Edge(nodeB, nodeD, 1.0));
        graph.addEdge(new Edge(nodeC, nodeD, 1.0));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeD);
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(3);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(nodeA);
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(nodeB);
        assertThat(bestPath.get(2).getTargetNode()).isEqualTo(nodeD);
    }

    @Test
    void maxWeightWorks() {
        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();
        graph.setIsDirected(true);

        Node nodeA = new Node("A");
        Node nodeB = new Node("B", null, 50.0);
        Node nodeC = new Node("C", null, 50.0);
        Node nodeD = new Node("D", null, 50.0);
        Node nodeE = new Node("E");

        Edge edge1 = new Edge(nodeA, nodeB, 51.0);
        Edge edge2 = new Edge(nodeA, nodeC, 49.0);
        Edge edge3 = new Edge(nodeA, nodeD, 51.0);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);

        graph.addEdge(new Edge(nodeB, nodeE, 1.0));
        graph.addEdge(new Edge(nodeC, nodeE, 1.0));
        graph.addEdge(new Edge(nodeD, nodeE, 1.0));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeE);
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(3);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(nodeA);
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(nodeC);
        assertThat(bestPath.get(2).getTargetNode()).isEqualTo(nodeE);
        
        assertThat(bestPath.get(1).getThroughEdge()).isEqualTo(edge2);
    }

    @Test
    void minWeightWorks() {
        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();
        graph.setIsDirected(true);

        Node nodeA = new Node("A");
        Node nodeB = new Node("B", 10.0, null);
        Node nodeC = new Node("C", 10.0, null);
        Node nodeD = new Node("D", 10.0, null);
        Node nodeE = new Node("E");

        Edge edge1 = new Edge(nodeA, nodeB, 5.0);
        Edge edge2 = new Edge(nodeA, nodeC, 11.0);
        Edge edge3 = new Edge(nodeA, nodeD, 9.0);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);

        graph.addEdge(new Edge(nodeB, nodeE, 1.0));
        graph.addEdge(new Edge(nodeC, nodeE, 1.0));
        graph.addEdge(new Edge(nodeD, nodeE, 1.0));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeE);
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(3);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(nodeA);
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(nodeC);
        assertThat(bestPath.get(2).getTargetNode()).isEqualTo(nodeE);
        
        assertThat(bestPath.get(1).getThroughEdge()).isEqualTo(edge2);
    }

    @Test
    void minAndMaxWeightWorks() {
        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = new Graph();
        graph.setIsDirected(true);

        Node nodeA = new Node("A");
        Node nodeB = new Node("B", 10.0, 110.0);
        Node nodeC = new Node("C", 20.0, 120.0);
        Node nodeD = new Node("D", 30.0, 130.0);
        Node nodeE = new Node("E", 40.0, 140.0);
        Node nodeF = new Node("F");

        Edge edge1 = new Edge(nodeA, nodeB, 9.0);
        Edge edge2 = new Edge(nodeA, nodeC, 121.0);
        Edge edge3 = new Edge(nodeA, nodeD, 50.0);
        Edge edge4 = new Edge(nodeA, nodeE, 39.0);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);

        graph.addEdge(new Edge(nodeB, nodeF, 1.0));
        graph.addEdge(new Edge(nodeC, nodeF, 1.0));
        graph.addEdge(new Edge(nodeD, nodeF, 1.0));
        graph.addEdge(new Edge(nodeE, nodeF, 1.0));

        graph.addNodes(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF));

        djp.setGraph(graph);

        // when
        djp.setStartNode(nodeA);
        djp.setEndNode(nodeF);
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(3);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(nodeA);
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(nodeD);
        assertThat(bestPath.get(2).getTargetNode()).isEqualTo(nodeF);
        
        assertThat(bestPath.get(1).getThroughEdge()).isEqualTo(edge3);
    }

    private Graph getGraphForGeneralTest4() {
        Graph graph = new Graph();
        graph.setIsDirected(true);

        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("5");
        Node node6 = new Node("6");
        Node node7 = new Node("7");
        Node node8 = new Node("8");
        Node node9 = new Node("9");
        Node node10 = new Node("10");
        Node node11 = new Node("11");

        Edge edge1 = new Edge(node1, node4, 3.0);
        Edge edge2 = new Edge(node1, node6, 7.0);
        Edge edge3 = new Edge(node2, node4, 13.0);
        Edge edge4 = new Edge(node2, node7, 37.0);
        Edge edge5 = new Edge(node3, node6, 11.0);
        Edge edge6 = new Edge(node4, node8, 12.0);
        Edge edge7 = new Edge(node5, node2, 12.0);
        Edge edge8 = new Edge(node5, node4, 3.0);
        Edge edge9 = new Edge(node5, node8, 70.0);
        Edge edge10 = new Edge(node6, node9, 59.0);
        Edge edge11 = new Edge(node7, node8, 27.0);
        Edge edge12 = new Edge(node7, node10, 70.0);
        Edge edge13 = new Edge(node8, node7, 16.0);
        Edge edge14 = new Edge(node8, node9, 57.0);
        Edge edge15 = new Edge(node9, node7, 79.0);
        Edge edge16 = new Edge(node9, node11, 75.0);
        Edge edge17 = new Edge(node9, node10, 80.0);

        graph.addNodes(Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8, node9, node10, node11));

        graph.addEdges(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9, edge10, edge11, edge12, edge13, edge14, edge15, edge16, edge17));

        return graph;
    }

    @Test
    void generalTest4_1() {
        // http://graphonline.ru/en/?graph=bWUWBVOuDPYlLiMM

        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = getGraphForGeneralTest4();

        djp.setGraph(graph);

        // when
        djp.setStartNode(graph.getNodeByName("5"));
        djp.setEndNode(graph.getNodeByName("10"));
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(5);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(graph.getNodeByName("5"));
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(graph.getNodeByName("4"));
        assertThat(bestPath.get(2).getTargetNode()).isEqualTo(graph.getNodeByName("8"));
        assertThat(bestPath.get(3).getTargetNode()).isEqualTo(graph.getNodeByName("7"));
        assertThat(bestPath.get(4).getTargetNode()).isEqualTo(graph.getNodeByName("10"));
    }

    @Test
    void generalTest4_2() {
        // http://graphonline.ru/en/?graph=bWUWBVOuDPYlLiMM

        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = getGraphForGeneralTest4();

        djp.setGraph(graph);

        // when
        djp.setStartNode(graph.getNodeByName("2"));
        djp.setEndNode(graph.getNodeByName("11"));
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(5);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(graph.getNodeByName("2"));
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(graph.getNodeByName("4"));
        assertThat(bestPath.get(2).getTargetNode()).isEqualTo(graph.getNodeByName("8"));
        assertThat(bestPath.get(3).getTargetNode()).isEqualTo(graph.getNodeByName("9"));
        assertThat(bestPath.get(4).getTargetNode()).isEqualTo(graph.getNodeByName("11"));
    }

    @Test
    void generalTest4_3() {
        // http://graphonline.ru/en/?graph=bWUWBVOuDPYlLiMM

        // given
        ShortestPathContract djp = new DijkstraShortestPath();

        Graph graph = getGraphForGeneralTest4();

        djp.setGraph(graph);

        // when
        djp.setStartNode(graph.getNodeByName("1"));
        djp.setEndNode(graph.getNodeByName("9"));
        djp.run();
        List<Visit> bestPath = djp.getBestPath();

        // then
        assertThat(bestPath.size()).isEqualTo(3);

        assertThat(bestPath.get(0).getTargetNode()).isEqualTo(graph.getNodeByName("1"));
        assertThat(bestPath.get(1).getTargetNode()).isEqualTo(graph.getNodeByName("6"));
        assertThat(bestPath.get(2).getTargetNode()).isEqualTo(graph.getNodeByName("9"));
    }
}
