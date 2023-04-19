package TSP.Prim;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PerfectMatchingTest {

    @Test
    void test1() {
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(10);
        Edge e1 = new Edge(0,1,12);
        Edge e2 = new Edge(0,2,10);
        Edge e3 = new Edge(0,3,19);
        Edge e4 = new Edge(0,4,8);
        Edge e5 = new Edge(1,2,3);
        Edge e6 = new Edge(1,3,7);
        Edge e7 = new Edge(1,4,2);
        Edge e8 = new Edge(2,3,6);
        Edge e9 = new Edge(2,4,4);
        Edge e10 = new Edge(3,4,4);

        edgeWeightedGraph.addEdge(e1);
        edgeWeightedGraph.addEdge(e2);
        edgeWeightedGraph.addEdge(e3);
        edgeWeightedGraph.addEdge(e4);
        edgeWeightedGraph.addEdge(e5);
        edgeWeightedGraph.addEdge(e6);
        edgeWeightedGraph.addEdge(e7);
        edgeWeightedGraph.addEdge(e8);
        edgeWeightedGraph.addEdge(e9);
        edgeWeightedGraph.addEdge(e10);

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0);
        arr.add(2);
        arr.add(3);
        arr.add(4);

       List<Edge> expectedEdgeList = new ArrayList<>();
       expectedEdgeList.add(e9);
       expectedEdgeList.add(e3);

        PerfectMatching perfectMatching = new PerfectMatching();
        List<Edge> actualEdgeList = perfectMatching.PerfectMatchingPairs(edgeWeightedGraph, arr);
        assertEquals(expectedEdgeList, actualEdgeList);
    }

    @Test
    void test2() {
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(7);
        Edge e1 = new Edge(0,1,7);
        Edge e2 = new Edge(0,2,8);
        Edge e3 = new Edge(1,2,3);
        Edge e4 = new Edge(1,3,6);
        Edge e5 = new Edge(2,3,4);
        Edge e6 = new Edge(2,4,3);
        Edge e7 = new Edge(3,5,5);
        Edge e8 = new Edge(4,5,2);
        Edge e9 = new Edge(3,4,2);

        edgeWeightedGraph.addEdge(e1);
        edgeWeightedGraph.addEdge(e2);
        edgeWeightedGraph.addEdge(e3);
        edgeWeightedGraph.addEdge(e4);
        edgeWeightedGraph.addEdge(e5);
        edgeWeightedGraph.addEdge(e6);
        edgeWeightedGraph.addEdge(e7);
        edgeWeightedGraph.addEdge(e8);
        edgeWeightedGraph.addEdge(e9);

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(4);
        arr.add(5);

        List<Edge> expectedEdgeList = new ArrayList<>();
        expectedEdgeList.add(e8);
        expectedEdgeList.add(e3);

        PerfectMatching perfectMatching = new PerfectMatching();
        List<Edge> actualEdgeList = perfectMatching.PerfectMatchingPairs(edgeWeightedGraph, arr);
        assertEquals(expectedEdgeList, actualEdgeList);
    }
}