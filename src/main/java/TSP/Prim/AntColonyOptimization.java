package TSP.Prim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AntColonyOptimization {

    private List<List<LondoncrimeDetailsEdge>> graph;
    private final int numVertices;
    private final int numAnts;
    private final Random random;

    private double[][] pheromoneLevels;
    private double[][] edgeWeights;
    private int[][] edges;
    private int[] tourLengths;

    private int[] bestTour;

    private int tours[];

    int numberOfAnts = 10;
    int numberOfIterations = 1000;
    double alpha = 1;
    double beta = 5;
    double evaporationRate = 0.5;

    double initialPheromoneLevel = 0.1;
    int Q = 100;

    double bestTourLength = Double.POSITIVE_INFINITY;



    public AntColonyOptimization(List<List<LondoncrimeDetailsEdge>> graph) {
        this.graph = graph;
        this.numVertices = graph.size();
        this.numAnts = numberOfAnts;
        this.random = new Random();
        this.tourLengths = new int[numAnts];
        initialize();
    }

    private void initialize() {
        pheromoneLevels = new double[numVertices][numVertices];
        edgeWeights = new double[numVertices][numVertices];
        edges = new int[numAnts][numVertices];
        for (int i = 0; i < numVertices; i++) {
            pheromoneLevels[i][i] = initialPheromoneLevel;
            for (int j = 0; j < numVertices; j++) {
                edgeWeights[i][j] = graph.get(i).get(j).getWeight();
            }
            execute();
            updateBestTour();
        }
    }

    public void execute() {
        for (int i = 0; i < numberOfIterations; i++) {
            for (int j = 0; j < numAnts; j++) {
                int startVertex = random.nextInt(numVertices);
                int[] tour = buildTour(startVertex);
                tourLengths[j] = getTourLength(tour);
                updatePheromoneLevels(numberOfAnts, tour);
            }
        }
    }

    private int getTourLength(int[] tour) {
        int tourLength = 0;
        int prevEdge = 0;
        for (int edge : tour) {
            tourLength += edge;
            prevEdge = tour[edge];
        }
        // Add distance from last edge back to start
        tourLength += prevEdge;
        return tourLength;
    }

    private int[] buildTour(int startVertex) {
        int[] tour = new int[numVertices];
        boolean[] visited = new boolean[numVertices];
        tour[0] = startVertex;
        visited[startVertex] = true;
        for (int i = 1; i < numVertices; i++) {
            int currentVertex = tour[i-1];
            int nextVertex = getNextVertex(currentVertex, visited);
            tour[i] = nextVertex;
            visited[nextVertex] = true;
        }
        return tour;
    }

    private int getNextVertex(int currentVertex, boolean[] visited) {
        double[] probabilities = calculateProbabilities(currentVertex, visited);
        double randomValue = random.nextDouble();
        double cumulativeProbability = 0.0;
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                cumulativeProbability += probabilities[i];
                if (cumulativeProbability >= randomValue) {
                    return i;
                }
            }
        }
        return -1;
    }

    private double[] calculateProbabilities(int currentVertex, boolean[] visited) {
        double[] probabilities = new double[numVertices];
        double denominator = 0.0;
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                denominator += Math.pow(pheromoneLevels[i][numVertices], alpha) * Math.pow(1.0 / edgeWeights[currentVertex][i], beta);
            }
        }
        for (int i = 0; i < numVertices; i++) {
            if (visited[i]) {
                probabilities[i] = 0.0;
            } else {
                double numerator = Math.pow(pheromoneLevels[i][numVertices], alpha) * Math.pow(1.0 / edgeWeights[currentVertex][i], beta);
                probabilities[i] = numerator / denominator;
            }
        }
        return probabilities;
    }

    private void updatePheromoneLevels(int ant, int[] tour) {
        for (int i = 0; i < numVertices - 1; i++) {
            int from = tour[i];
            int to = tour[i + 1];
            pheromoneLevels[from][to] = (1 - evaporationRate) * pheromoneLevels[from][to] + evaporationRate * (Q / tourLengths[ant]);
            pheromoneLevels[to][from] = pheromoneLevels[from][to]; // pheromones on undirected edges
        }
    }

    private void updateBestTour() {
        double shortestTourLength = Double.MAX_VALUE;
        int[] shortestTour = null;
        for (int ant = 0; ant < numAnts; ant++) {
            if (tourLengths[ant] < shortestTourLength) {
                shortestTourLength = tourLengths[ant];
                shortestTour = tours;
            }
        }
        if (shortestTourLength < bestTourLength) {
            bestTourLength = shortestTourLength;
            bestTour = shortestTour;
        }
    }

    public static void main(String[] args) {

        LondoncrimeDetailsEdge londoncrimeDetailsEdge = new LondoncrimeDetailsEdge();
        List<List<LondoncrimeDetailsEdge>> masterVerticesToPrim = new ArrayList<>();
        masterVerticesToPrim = londoncrimeDetailsEdge.getEdgeInfo();

        int numVertices = masterVerticesToPrim.size();

        double[][] edgeWeights = new double[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            List<LondoncrimeDetailsEdge> edgesFromVertex = masterVerticesToPrim.get(i);
            for (LondoncrimeDetailsEdge edge : edgesFromVertex) {
                int j = edge.getL2().getRepresentation(); // subtract 1 because vertex IDs are 1-based
                double weight = edge.getWeight();
                edgeWeights[i][j] = weight;
                edgeWeights[j][i] = weight; // assume graph is undirected
            }
        }

        AntColonyOptimization aco = new AntColonyOptimization(masterVerticesToPrim);
        aco.initialize();
    }

}
