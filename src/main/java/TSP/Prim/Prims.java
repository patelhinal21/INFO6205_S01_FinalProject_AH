package TSP.Prim;

import Optimization.TwoOpt;

import java.util.*;

public class Prims {
    private boolean[] marked; // MST vertices
    private Queue<Edge> mst; // MST edges
    private MinPQ<Edge> pq; // crossing (and ineligible) edges

    public Prims(EdgeWeightedGraph G) {
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        mst = new LinkedList<>();
        visit(G, 0); // assumes G is connected
        while (!pq.isEmpty()) {
            Edge e = pq.delMin(); // Get lowest-weight
            int v = e.either(), w = e.other(v); // edge from pq.
            if (marked[v] && marked[w]) continue; // Skip if ineligible.
            mst.add(e); // Add edge to tree.
            if (!marked[v]) visit(G, v); // Add vertex to tree
            if (!marked[w]) visit(G, w); // (either v or w).
        }
    }

    private void visit(EdgeWeightedGraph G, int v) { // Mark v and add to pq all edges from v to unmarked vertices.
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)]) pq.insert(e);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {


        CityDataHelper helper = new CityDataHelper();
        List<CityDetails> cityDetailsList = helper.cityList();
        HashMap<String, Double> cityWeightMap = helper.cityDistances(cityDetailsList);
        System.out.println("city Weights "+ cityWeightMap);
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(156);

        for (int i = 0; i < 155; i++) {
            for (int j = i + 1; j < 156; j++) {
                double val = cityWeightMap.get("" + i + "-" + "" + j);
                Edge e = new Edge(i, j, val);
                edgeWeightedGraph.addEdge(e);
            }
        }

//        System.out.println("edge weighted graph in loop " + edgeWeightedGraph);
//        System.out.println("edge weighted graph in loop " + edgeWeightedGraph);
//        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(5);
//        Edge e1 = new Edge(0, 1, 12);
//        Edge e2 = new Edge(0, 2, 10);
//        Edge e3 = new Edge(0, 3, 19);
//        Edge e4 = new Edge(0, 4, 8);
//        Edge e5 = new Edge(1, 2, 3);
//        Edge e6 = new Edge(1, 3, 7);
//        Edge e7 = new Edge(1, 4, 2);
//        Edge e8 = new Edge(2, 3, 6);
//        Edge e9 = new Edge(2, 4, 4);
//        Edge e10 = new Edge(3, 4, 4);
//
//        edgeWeightedGraph.addEdge(e1);
//        edgeWeightedGraph.addEdge(e2);
//        edgeWeightedGraph.addEdge(e3);
//        edgeWeightedGraph.addEdge(e4);
//        edgeWeightedGraph.addEdge(e5);
//        edgeWeightedGraph.addEdge(e6);
//        edgeWeightedGraph.addEdge(e7);
//        edgeWeightedGraph.addEdge(e8);
//        edgeWeightedGraph.addEdge(e9);
//        edgeWeightedGraph.addEdge(e10);



        Prims prims = new Prims(edgeWeightedGraph);
        System.out.println("number of vertices " + edgeWeightedGraph.V());
//        System.out.println("city details size " + cityDetailsList.size());

        System.out.println("minimum spanning tree " + prims.mst);
        HashMap<Integer, Integer> oddEvenVertices = FindOddVertices.getOddEvenVertices(prims.mst);
        System.out.println("odd even vertices " + oddEvenVertices);
        ArrayList<Integer> oddVerticesArrayList = new ArrayList<>();
        oddVerticesArrayList = FindOddVertices.oddVertices(oddEvenVertices);
        System.out.println("array entries of odd vertices " + oddVerticesArrayList);

        List<Edge> pairs = PerfectMatching.PerfectMatchingPairs(edgeWeightedGraph, oddVerticesArrayList);
        System.out.println("pairs " + pairs);
        System.out.println( "number of pairs " + pairs.size());
        prims.mst.addAll(pairs);
        System.out.println("multigraph after adding pairs " + prims.mst);

        EulerianCycle eu = new EulerianCycle(prims.mst);
        System.out.println("printing Eulerian cycle " + eu.eulerianCycle());
        System.out.println("has Eulerian cycle " + eu.hasEulerianCycle());
        Queue<Integer> eulerTour = eu.eulerianCycle();
        System.out.println("euler tour " + eulerTour);
        List<Integer> hamiltonianCircuitPathList = eu.hamiltonianCircuitPath(eulerTour);
        System.out.println(" hamilton tour path " + hamiltonianCircuitPathList);
        double hamiltonianCircuitTourWeight = eu.hamiltonianCircuitTourWeight(hamiltonianCircuitPathList,cityDetailsList);
        System.out.println(" hamilton tour path weight " + hamiltonianCircuitTourWeight);
        TwoOpt twoOptObject = new TwoOpt();
        //twoOptObject.twoOptCalculation(hamiltonianCircuitPathList,cityWeightMap);
        System.out.println("inside twoOptCalculation method "+ twoOptObject.twoOptCalculation(hamiltonianCircuitPathList,cityWeightMap,hamiltonianCircuitTourWeight));
    }
}
