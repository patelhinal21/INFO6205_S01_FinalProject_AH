package TSP.Prim;

import java.util.*;

public class EulerianCycle {
    private Queue<Integer> cycle = new LinkedList<>();
    private int[] degree;

    private Queue<Edge> multigraph;

    public EulerianCycle() {
    }

    public EulerianCycle(Queue<Edge> multigraph) {
        this.multigraph = multigraph;
        int V = getNumberOfVertices(multigraph);
        System.out.println("inside euler cycle size of multigraph " + V);
        degree = new int[V];

        // Create adjacency list and count degree of each vertex
        List<Edge>[] adj = createAdjacencyList(multigraph);
        for (int v = 0; v < V; v++) {
            for (Edge e : adj[v]) {
                degree[v]++;
            }
        }

        // Find a vertex with nonzero degree (if it exists)
        int min = 0;
        int max = 584;
        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
//        int s = 0;
        int s = random_int;
        System.out.println("value of s in Eulerian cycle which is the value of starting vertex " + s);

        while (s < V && degree[s] % 2 == 0) {
            s++;
        }
        if (s == V) {
            // Graph has even degree, so start anywhere
//            s = 0;
            s = random_int;

        }

        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (!adj[v].isEmpty()) {
                Edge e = adj[v].remove(0);
//                System.out.println("edge " + e);
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

    public Queue<Integer> eulerianCycle() {
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

    public List<Integer> hamiltonianCircuitPath(Queue<Integer> eulerianCycle  ) {


        int firstVertex = eulerianCycle.element();

        Set<Integer> removeDuplicates = new LinkedHashSet<>(eulerianCycle);
        System.out.println("hamilton circuit " + removeDuplicates);
        List<Integer> listVertices = new ArrayList<>(removeDuplicates.stream().toList());
        listVertices.add(firstVertex);
        System.out.println("after adding initial vertex " + listVertices);

       return listVertices;
    }

    public double hamiltonianCircuitTourWeight(List<Integer> listVertices, List<List<LondoncrimeDetailsEdge>> masterVerticesToPrim){
        double tourWeight = 0.0;
        for (int i = 0; i < listVertices.size()-1; i++) {
            int a = listVertices.get(i);
            int b = listVertices.get(i + 1);
            double distanceUsingFormula = getDistance(a,b, masterVerticesToPrim);
            tourWeight = tourWeight + distanceUsingFormula;
        }

        System.out.println("tour weight " + tourWeight);

        return tourWeight;
    }
    public double getDistance(int a, int b, List<List<LondoncrimeDetailsEdge>> masterVerticesToPrim) {
        System.out.println("inside get distance");
        if(b > a) {
            b = b-1;
        }
        double weight = masterVerticesToPrim.get(a).get(b).getWeight();
        return weight;
    }

    public static void main(String[] args) {
        EulerianCycle eCycle = new EulerianCycle();
        double lat1 = 51.514195;
        double lat2 = 51.515753;
        double lgn1 = -0.145735;
        double lgn2 = -0.143092;

        LondoncrimeDetailsEdge londoncrimeDetailsEdge = new LondoncrimeDetailsEdge();
        List<List<LondoncrimeDetailsEdge>> masterVerticesToPrim = new ArrayList<>();
        masterVerticesToPrim = londoncrimeDetailsEdge.getEdgeInfo();
        double weight = eCycle.getDistance(3, 279, masterVerticesToPrim);
        System.out.println("weight inside get distance " + weight);
    }






}