package Optimization;

import java.util.*;

import TSP.Prim.*;
public class TwoOpt {

    public Map<String, Object> twoOptCalculation(List<Integer> tour, List<List<LondoncrimeDetailsEdge>> masterVerticesToPrim, double hamiltonianCircuitTourWeight) {
        int n = tour.size();
        boolean improve = true;
        double tourWeight = hamiltonianCircuitTourWeight;
        while (improve) {
            improve = false;
            for (int i = 0; i < n - 2; i++) {
                for (int j = i + 2; j < n-1; j++) {
                    double dist1 = getDistance(masterVerticesToPrim, tour.get(i), tour.get(i+1));
                    double dist2 = getDistance(masterVerticesToPrim, tour.get(j), tour.get((j+1)%n));
                    double dist3 = getDistance(masterVerticesToPrim, tour.get(i), tour.get(j));
                    double dist4 = getDistance(masterVerticesToPrim, tour.get(i+1), tour.get((j+1)%n));
                    double delta = (dist3 + dist4) - (dist1 + dist2);
//                    System.out.println("distances1 "+ dist1);
//                    System.out.println("distances2 "+ dist2);
//                    System.out.println("distances3 "+ dist3);
//                    System.out.println("distances4 " + dist4);
                    //System.out.println("delta value "+ delta);
                    if (delta < 0.0) {
                        tour = reverse(tour, i+1, j);
                        //System.out.println("reverse tour "+ tour);
                        tourWeight = tourWeight + delta; // update tour weight
                        improve = true;
                    }
                    //System.out.println( "i " + i + "-" + " j " + j);
                }

            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("tourWeight", tourWeight);
        result.put("tour", tour);
        return result;

    }


    private static double getDistance(List<List<LondoncrimeDetailsEdge>> masterVerticesToPrim, int u, int v) {
        if(v > u) {
            v = v-1;
        }
        double weight = masterVerticesToPrim.get(u).get(v).getWeight();
        // If the edge is not found, return a large value as infinity

        return weight;
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

