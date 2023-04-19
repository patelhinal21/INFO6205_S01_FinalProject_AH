package Optimization;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import TSP.Prim.LondoncrimeDetailsEdge;
public class SimulatedAnnealing {
    private List<List<LondoncrimeDetailsEdge>> masterVerticesToPrim;
    private Random random;
    private double temperature;
    private double coolingRate;
    private int[] currentSolution;

    public SimulatedAnnealing(List<List<LondoncrimeDetailsEdge>> masterVerticesToPrim, double temperature, double coolingRate) {
        this.masterVerticesToPrim = masterVerticesToPrim;
        this.random = new Random();
        this.temperature = temperature;
        this.coolingRate = coolingRate;
        this.currentSolution = new int[masterVerticesToPrim.size()];
        for (int i = 0; i < currentSolution.length; i++) {
            currentSolution[i] = i;
        }
        shuffleArray(currentSolution);
    }

    public int[] solve() {
        int[] bestSolution = currentSolution;
        while (temperature > 1) {
            int[] newSolution = Arrays.copyOf(currentSolution, currentSolution.length);
            int swapIndex1 = random.nextInt(newSolution.length);
            int swapIndex2 = random.nextInt(newSolution.length);
            int temp = newSolution[swapIndex1];
            newSolution[swapIndex1] = newSolution[swapIndex2];
            newSolution[swapIndex2] = temp;
            double currentEnergy = calculateEnergy(currentSolution);
            double newEnergy = calculateEnergy(newSolution);
            if (acceptanceProbability(currentEnergy, newEnergy, temperature) > Math.random()) {
                currentSolution = newSolution;
            }
            if (calculateDistance(currentSolution) < calculateDistance(bestSolution)) {
                bestSolution = currentSolution;
            }
            temperature *= 1 - coolingRate;
        }
        double tourWeightInSA =calculateDistance(bestSolution);
        System.out.println(tourWeightInSA);
        return bestSolution;

    }

    private double calculateEnergy(int[] solution) {
        double distance = calculateDistance(solution);
        return distance;
    }

    private double acceptanceProbability(double currentEnergy, double newEnergy, double temperature) {
        if (newEnergy < currentEnergy) {
            return 1;
        }
        return Math.exp((currentEnergy - newEnergy) / temperature);
    }

    private static double getDistance(List<List<LondoncrimeDetailsEdge>> masterVerticesToPrim, int u, int v) {
        if(v > u) {
            v = v-1;
        }
        double weight = masterVerticesToPrim.get(u).get(v).getWeight();
        // If the edge is not found, return a large value as infinity

        return weight;
    }
    private double calculateDistance(int[] solution) {
        double distance = 0;
        for (int i = 0; i < solution.length - 1; i++) {
            distance += getDistance(masterVerticesToPrim, solution[i], solution[i+1]);
        }
        distance += getDistance(masterVerticesToPrim, solution[solution.length-1], solution[0]);
        return distance;
    }


    private void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}

