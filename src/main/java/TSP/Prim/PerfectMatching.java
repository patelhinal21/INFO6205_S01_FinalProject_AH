package TSP.Prim;

import java.util.*;

public class PerfectMatching {

    public static HashMap<Edge, Double> PerfectMatchingPairs(EdgeWeightedGraph G, ArrayList<Integer> A) {
        int i = 0;
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
        System.out.println("Before sorting " + edgesForPerfectMatching);

//        method to sort edges
        List<Map.Entry<Edge, Double>> listValues = new LinkedList<>(edgesForPerfectMatching.entrySet());
        listValues.sort(Map.Entry.comparingByValue());

        HashMap<Edge, Double> sortedHashMap = new LinkedHashMap<>();
        for (Map.Entry<Edge, Double> itr : listValues) {
            sortedHashMap.put(itr.getKey(), itr.getValue());
        }
        System.out.println("After sorting " + sortedHashMap);
        pairsToAdd(sortedHashMap);
        return sortedHashMap;
    }

    public static void pairsToAdd (HashMap<Edge, Double> sortedEdges) {
        Map.Entry<Edge, Double> actualValue = sortedEdges.entrySet()
                .stream()
                .findFirst()
                .get();
        Edge e1 = actualValue.getKey();
        int i1 = e1.either();
        int i2 = e1.other(i1);
        System.out.println("actual value " + actualValue);
    }
}
