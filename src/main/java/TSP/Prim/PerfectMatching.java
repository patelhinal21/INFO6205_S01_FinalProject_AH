package TSP.Prim;

import java.util.*;

public class PerfectMatching {

    public static List<Edge> PerfectMatchingPairs(EdgeWeightedGraph G, ArrayList<Integer> A) {
        int i = 0;
        int oddVerticesNumber = A.size();
//        method to find edges of odd vertices
        HashMap<Edge, Double> edgesForPerfectMatching = new HashMap<>();
        for (Integer o : A) {
            for (Edge e : G.adj(o)) {
                int v1 = o;
                double weight = e.getWeight();
                if (e.other(v1) != 1) {
                    edgesForPerfectMatching.put(e, weight);
                }
            }
        }

//         method to get all edges and sort
        TreeMap<Edge, Double> edgesForPerfectMatching1 = new TreeMap<>();
        for (Edge e : G.adj(A.indexOf(i))) {
            double weight = e.getWeight();
            edgesForPerfectMatching1.put(e, weight);
        }

        System.out.println("tree map " + edgesForPerfectMatching1);


//        method to sort edges
        List<Map.Entry<Edge, Double>> listValues = new LinkedList<>(edgesForPerfectMatching.entrySet());
        listValues.sort(Map.Entry.comparingByValue());

        HashMap<Edge, Double> sortedHashMap = new LinkedHashMap<>();
        for (Map.Entry<Edge, Double> itr : listValues) {
            sortedHashMap.put(itr.getKey(), itr.getValue());
        }

        List<Edge> pairsOfEdges = pairsToAdd(sortedHashMap, oddVerticesNumber);
        return pairsOfEdges;
    }

    public static List<Edge> pairsToAdd (HashMap<Edge, Double> sortedEdges, int oddVerticesNumber) {
        List<Edge> edgesFromPerfectMatching = new LinkedList<>();

        for(int i =0 ; i < oddVerticesNumber/2; i++) {
            Map.Entry<Edge, Double> actualValue = sortedEdges.entrySet()
                    .stream()
                    .findFirst()
                    .get();
            Edge e1 = actualValue.getKey();
            Edge e2 = null;
            edgesFromPerfectMatching.add(e1);
            int i1 = e1.either();
            int i2 = e1.other(i1);

            Iterator<Map.Entry<Edge, Double>> itr = sortedEdges.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<Edge, Double> entry = itr.next();
                e2 = entry.getKey();
                int i3 = e2.either();
                int i4 = e2.other(i3);
                if (i1 == i3 || i1 == i4 || i2 == i3 || i2 == i4) {
                    itr.remove();
                }
            }
        }
        return edgesFromPerfectMatching;
    }
}
