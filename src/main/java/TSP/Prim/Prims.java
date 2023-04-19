package TSP.Prim;

import Optimization.ThreeOpt;
import Optimization.TwoOpt;

import java.util.*;

public class Prims {
    private boolean[] marked; // MST vertices
    Queue<Edge> mst; // MST edges
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

//    public static void main(String[] args) {
//
//        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(584);
//
//        LondoncrimeDetailsEdge londoncrimeDetailsEdge = new LondoncrimeDetailsEdge();
//        List<List<LondoncrimeDetailsEdge>> masterVerticesToPrim = new ArrayList<>();
//        masterVerticesToPrim = londoncrimeDetailsEdge.getEdgeInfo();
//        System.out.println("size of masterVerticesPrim " + masterVerticesToPrim.size());
//
//
//        for(int i = 0; i < 583; i++)
//        {
//            for(int j = i; j< 583; j++)
//            {
//                int v1 = masterVerticesToPrim.get(i).get(j).getL1().getRepresentation();
//                int v2 = masterVerticesToPrim.get(i).get(j).getL2().getRepresentation();
//                double weight = masterVerticesToPrim.get(i).get(j).getWeight();
//                Edge e = new Edge(v1, v2, weight);
//                edgeWeightedGraph.addEdge(e);
//            }
//        }
//
//        Prims prims = new Prims(edgeWeightedGraph);
//        System.out.println("number of vertices " + edgeWeightedGraph.V());
//
//        System.out.println("minimum spanning tree" + prims.mst);
//        double weightOfMst = 0;
//        for(Edge e: prims.mst)
//
//        {
//            weightOfMst = weightOfMst + e.getWeight();
//        }
//        System.out.println("weight of mst " + weightOfMst);
//
//        HashMap<Integer, Integer> oddEvenVertices = FindOddVertices.getOddEvenVertices(prims.mst);
//        System.out.println("odd even vertices " + oddEvenVertices);
//        ArrayList<Integer> oddVerticesArrayList = new ArrayList<>();
//        oddVerticesArrayList = FindOddVertices.oddVertices(oddEvenVertices);
//        System.out.println("array entries of odd vertices " + oddVerticesArrayList);
//
//        List<Edge> pairs = PerfectMatching.PerfectMatchingPairs(edgeWeightedGraph, oddVerticesArrayList);
//        System.out.println("pairs " + pairs);
//        System.out.println( "number of pairs " + pairs.size());
//        prims.mst.addAll(pairs);
//        System.out.println("multigraph after adding pairs " + prims.mst);
//
//        EulerianCycle eu = new EulerianCycle(prims.mst);
//        System.out.println("printing Eulerian cycle " + eu.eulerianCycle());
//        System.out.println("has Eulerian cycle " + eu.hasEulerianCycle());
//        Queue<Integer> eulerTour = eu.eulerianCycle();
//        System.out.println("euler tour " + eulerTour);
//        List<Integer> hamiltonianCircuitPathList = eu.hamiltonianCircuitPath(eulerTour);
//        System.out.println(" hamilton tour path " + hamiltonianCircuitPathList + " " + "size " + hamiltonianCircuitPathList.size());
//        double hamiltonianCircuitTourWeight = eu.hamiltonianCircuitTourWeight(hamiltonianCircuitPathList,masterVerticesToPrim);
//        System.out.println(" hamilton tour path weight " + hamiltonianCircuitTourWeight);
//        TwoOpt twoOptObject = new TwoOpt();
//        System.out.println("inside twoOptCalculation method "+ twoOptObject.twoOptCalculation(hamiltonianCircuitPathList,masterVerticesToPrim,hamiltonianCircuitTourWeight));
//        ThreeOpt threeOptObject = new ThreeOpt();
//        System.out.println("inside threeOptCalculation method "+ threeOptObject.threeOptCalculation(hamiltonianCircuitPathList,masterVerticesToPrim,hamiltonianCircuitTourWeight));
//
//
//    }
}
