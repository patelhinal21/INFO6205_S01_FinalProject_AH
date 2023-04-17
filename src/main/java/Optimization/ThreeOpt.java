package Optimization;

import java.util.*;

public class ThreeOpt {

    public Map<String, Object> threeOptCalculation(List<Integer> tour, HashMap<String, Double> edges, double hamiltonianCircuitTourWeight) {
        int n = tour.size();
        boolean improve = true;
        double tourWeight = hamiltonianCircuitTourWeight;
        while (improve) {
            improve = false;
            for (int i = 0; i < n - 4; i++) {
                for (int j = i + 2; j < n - 2; j++) {
                    for (int k = j + 2; k < n; k++) {
                        double dist1 = getDistance(edges, tour.get(i), tour.get(i+1));
                        double dist2 = getDistance(edges, tour.get(j), tour.get(j+1));
                        double dist3 = getDistance(edges, tour.get(k), tour.get((k+1)%n));
                        double dist4 = getDistance(edges, tour.get(i), tour.get(j));
                        double dist5 = getDistance(edges, tour.get(i+1), tour.get(j+1));
                        double dist6 = getDistance(edges, tour.get(j), tour.get(k));
                        double dist7 = getDistance(edges, tour.get(i), tour.get(k));
                        double dist8 = getDistance(edges, tour.get(i+1), tour.get((k+1)%n));
                        double delta1 = (dist1 + dist2 + dist3) - (dist4 + dist5 + dist3);
                        double delta2 = (dist1 + dist2 + dist3) - (dist6 + dist5 + dist7);
                        double delta3 = (dist1 + dist2 + dist3) - (dist4 + dist8 + dist7);
                        if (delta1 < -1.0 || delta2 < -1.0 || delta3 < -1.0) {
                            if (delta1 < delta2 && delta1 < delta3) {
                                tour = reverse(tour, i+1, j);
                                tour = reverse(tour, j+1, k);
                                tourWeight = tourWeight + delta1;
                            } else if (delta2 < delta1 && delta2 < delta3) {
                                tour = reverse(tour, j+1, k);
                                tour = reverse(tour, i+1, j);
                                tourWeight = tourWeight + delta2;
                            } else {
                                tour = reverse(tour, i+1, k);
                                tourWeight = tourWeight + delta3;
                            }
                            improve = true;
                        }
                    }
                }
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("tourWeight", tourWeight);
        result.put("tour", tour);
        return result;
    }

    private static double getDistance(HashMap<String, Double> edges, int u, int v) {
        String edgeKey = u + "-" + v;
        if (edges.containsKey(edgeKey)) {
            return edges.get(edgeKey);
        }
        return Double.POSITIVE_INFINITY;
    }

    private static List<Integer> reverse(List<Integer> arr, int i, int j) {
        while (i < j) {
            int temp = arr.get(i);
            arr.set(i, arr.get(j));
            arr.set(j, temp);
            i++;
            j--;
        }
        return arr;
    }
}
