package TSP.Prim;

import java.util.*;

public class EulerianCycle {
    private Queue<Integer> cycle = new LinkedList<>();
    private int[] degree;

    public EulerianCycle(Queue<Edge> multigraph) {
        int V = getNumberOfVertices(multigraph);
        degree = new int[V];

        // Create adjacency list and count degree of each vertex
        List<Edge>[] adj = createAdjacencyList(multigraph);
        for (int v = 0; v < V; v++) {
            for (Edge e : adj[v]) {
                degree[v]++;
            }
        }

        // Find a vertex with nonzero degree (if it exists)
        int s = 0;
        while (s < V && degree[s] % 2 == 0) {
            s++;
        }
        if (s == V) {
            // Graph has even degree, so start anywhere
            s = 0;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (!adj[v].isEmpty()) {
                Edge e = adj[v].remove(0);
                int w = e.other(v);
                stack.push(v);
                v = w;
            }
            cycle.add(v);
        }
        System.out.println("printing cycle " + cycle);

        // Check that all edges have been visited
        if (cycle.size() != getNumberOfEdges(multigraph) + 1) {
            cycle = null;
        }
    }

    public boolean hasEulerianCycle() {
        return cycle != null;
    }

    public Iterable<Integer> eulerianCycle() {
        return cycle;
    }

    private int getNumberOfVertices(Queue<Edge> multigraph) {
        Set<Integer> vertices = new HashSet<>();
        for (Edge e : multigraph) {
            vertices.add(e.either());
            vertices.add(e.other(e.either()));
        }
        return vertices.size();
    }

    private int getNumberOfEdges(Queue<Edge> multigraph) {
        return multigraph.size() * 2;
    }

    private List<Edge>[] createAdjacencyList(Queue<Edge> multigraph) {
        int V = getNumberOfVertices(multigraph);
        List<Edge>[] adj = new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
        for (Edge e : multigraph) {
            int v = e.either();
            int w = e.other(v);
            adj[v].add(e);
            adj[w].add(e);
        }
        return adj;
    }
}