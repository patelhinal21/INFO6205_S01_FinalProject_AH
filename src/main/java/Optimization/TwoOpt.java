package Optimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import TSP.Prim.*;
public class TwoOpt {




    public static int[] twoOpt(int[] tour, int[][] edges) {
        int n = tour.length;
        boolean improve = true;
        while (improve) {
            improve = false;
            for (int i = 0; i < n - 2; i++) {
                for (int j = i + 2; j < n; j++) {
                    int dist1 = getDistance(edges, tour[i], tour[i+1]);
                    int dist2 = getDistance(edges, tour[j], tour[(j+1)%n]);
                    int dist3 = getDistance(edges, tour[i], tour[j]);
                    int dist4 = getDistance(edges, tour[i+1], tour[(j+1)%n]);
                    int delta = (dist1 + dist2) - (dist3 + dist4);
                    if (delta < 0) {
                        tour = reverse(tour, i+1, j);
                        improve = true;
                    }
                }
            }
        }

        return tour;
    }

    private static int getDistance(int[][] edges, int u, int v) {
        for (int[] edge : edges) {
            if ((edge[0] == u && edge[1] == v) || (edge[0] == v && edge[1] == u)) {
                return edge[2];
            }
        }
        return -1; // distance not found
    }

    private static int[] reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1, 10},
                {0, 2, 15},
                {0, 3, 20},
                {1, 2, 35},
                {1, 3, 25},
                {2, 3, 30}
        };
        int[] tour = {0, 1, 2, 3};
        System.out.println(Arrays.toString(twoOpt(tour, edges)));

    }
}
