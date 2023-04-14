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
        int max = 155;
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

    public double hamiltonianCircuit(Queue<Integer> eulerianCycle , List<CityDetails> places ) {

        double tourWeight = 0;
        int firstVertex = eulerianCycle.element();

        Set<Integer> removeDuplicates = new LinkedHashSet<>(eulerianCycle);
        System.out.println("hamilton circuit " + removeDuplicates);
        List<Integer> listVertices = new ArrayList<>(removeDuplicates.stream().toList());
        listVertices.add(firstVertex);
        System.out.println("after adding initial vertex " + listVertices);

        for (int i = 0; i < listVertices.size() - 1; i++) {
            int a = listVertices.get(i);
            int b = listVertices.get(i + 1);
//            System.out.println("a " + a);
//            System.out.println("b " + b);
            double distanceUsingFormula = getDistance(a,b, places);
//            System.out.println("distance in km for given points " + a + " and " + b + " is " + distanceUsingFormula);
            tourWeight = tourWeight + distanceUsingFormula;
        }

        System.out.println("tour weight " + tourWeight);

        return tourWeight;
    }

    public double getDistance(int a, int b, List<CityDetails> places) {

        double lat1 = places.get(a).getLat();
        double lng1 = places.get(a).getLng();

        double lat2 = places.get(b).getLat();
        double lng2 = places.get(b).getLng();

        double result = distanceUsingFormula(lat1, lat2, lng1,lng2);


        return result;
    }

    public double distanceUsingFormula(double lat1, double lat2, double lng1, double lng2) {
//        System.out.println("lat1 " + lat1 + " lat2 " + lat2 + " lng1 " + lng1 + " lng2 " + lng2);
        lng1 = Math.toRadians(lng1);
        lng2 = Math.toRadians(lng2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

//        System.out.println("after radian conversion " + "lat1 " + lat1 + " lat2 " + lat2 + " lng1 " + lng1 + " lng2 " + lng2);

        // Haversine formula
        double dlon = lng2 - lng1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }

    public static void main(String[] args) {
        EulerianCycle eCycle = new EulerianCycle();
        double lat1 = 51.514195;
        double lat2 = 51.515753;
        double lgn1 = -0.145735;
        double lgn2 = -0.143092;

        double weight = eCycle.distanceUsingFormula(lat1, lat2, lgn1,lgn2);
        System.out.println(weight);
    }






}