package TSP.Prim;

import java.util.*;

public class EulerianTour {

    public EulerianTour(Queue<Edge> mst) {
    }

    public static List<Integer> eulerianTour(Queue<Edge> mst) {
        // Create an adjacency list from the minimum spanning tree
        List<Integer>[] adjList = buildAdjList(mst);

        // Initialize a stack to store the tour
        Stack<Integer> tour = new Stack<>();

        // Start the tour from any vertex
        int start = mst.peek().either();
        tour.push(start);

        // Repeat until all edges have been traversed
        while (!mst.isEmpty()) {
            Edge e = mst.remove();
            int u = e.either(), v = e.other(u);
            tour.push(v);
            // Remove the edge from the adjacency list
            adjList[u].remove((Integer) v);
            adjList[v].remove((Integer) u);
            // Find a cycle and add it to the tour
            while (!tour.isEmpty()) {
                int x = tour.peek();
                if (!adjList[x].isEmpty()) {
                    tour.push(adjList[x].remove(0));
                } else {
                    break;
                }
            }
        }

        // Convert the stack to a list and return it
        List<Integer> result = new ArrayList<>(tour);
        Collections.reverse(result);
        return result;
    }

    // Build an adjacency list from the minimum spanning tree
    public static List<Integer>[] buildAdjList(Queue<Edge> mst) {
        int n = -1;
        for (Edge e : mst) {
            n = Math.max(n, Math.max(e.either(), e.other(e.either())));
        }
        List<Integer>[] adjList = new List[n+1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (Edge e : mst) {
            adjList[e.either()].add(e.other(e.either()));
            adjList[e.other(e.either())].add(e.either());
        }
        return adjList;
    }

//    public static List<Integer> hamiltonianCircuit(List<Integer> eulerianTour) {
//        Set<Integer> visited = new HashSet<>();
//        List<Integer> hamiltonianCircuit = new ArrayList<>();
//
//        for (int vertex : eulerianTour) {
//            if (!visited.contains(vertex)) {
//                hamiltonianCircuit.add(vertex);
//                visited.add(vertex);
//            }
//        }
//
//        // add the starting vertex to complete the circuit
//        hamiltonianCircuit.add(eulerianTour.get(0));
//        return hamiltonianCircuit;
//    }


}
