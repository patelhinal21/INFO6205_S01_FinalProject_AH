package TSP.Prim;

import java.util.*;

public class PerfectMatching {

    public static HashMap<Edge, Double> PerfectMatchingPairs(EdgeWeightedGraph G, ArrayList A){
        int i=0;
        HashMap<Edge, Double> EdgesForPerfectMatching = new HashMap<>();
        for (Edge e : G.adj(A.indexOf(i))){
            double weight= e.getWeight();
            EdgesForPerfectMatching.put(e,weight);

        }

        System.out.println("Before sorting " + EdgesForPerfectMatching);
        List<Double> sortWeight = new ArrayList<>(EdgesForPerfectMatching.values());
        EdgesForPerfectMatching.entrySet()
                .stream()
                .sorted(Map.Entry.<Edge, Double>comparingByValue())
                .forEach(System.out::println);

        System.out.println("After sorting " +EdgesForPerfectMatching);
        return EdgesForPerfectMatching;
    }


}
