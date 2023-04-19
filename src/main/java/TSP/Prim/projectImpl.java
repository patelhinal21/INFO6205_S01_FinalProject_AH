package TSP.Prim;

import Optimization.ThreeOpt;
import Optimization.TwoOpt;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class projectImpl {
    public projectImpl() {
    }

    public HashMap<Integer, String> sampleArrayList1 = new LinkedHashMap<>();
    public List<String> tourRepresentation = new ArrayList<>();

    public HashMap<Integer, String> convertingDataToArrayList() {
        String line = "";
        String csvSplitBy = ",";

        File fileObj = new File("/Users/aishwaryavenkatesan/Desktop/programming struct algo/INFO6205_S01_FinalProject_AH/src/main/java/TSP/Prim/psasampledatafinal.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(fileObj))) {
            while ((line = br.readLine()) != null) {
                String data[] = line.split(csvSplitBy);

                String crimeId = data[0];
                int repId = Integer.parseInt(data[3]);

                String crimeNew = StringUtils.right(crimeId, 5);

                sampleArrayList1.put(repId, crimeNew);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sampleArrayList1;
    }

    public List<String> representationOfTour(List<Integer> hamiltonCircuit, HashMap<Integer, String> actualValues) {

        for (int i : hamiltonCircuit) {
            String val = actualValues.get(i);
            tourRepresentation.add(val);
        }

        return tourRepresentation;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(584);

        LondoncrimeDetailsEdge londoncrimeDetailsEdge = new LondoncrimeDetailsEdge();
        List<List<LondoncrimeDetailsEdge>> masterVerticesToPrim = new ArrayList<>();
        masterVerticesToPrim = londoncrimeDetailsEdge.getEdgeInfo();


        for (int i = 0; i < 583; i++) {
            for (int j = i; j < 583; j++) {
                int v1 = masterVerticesToPrim.get(i).get(j).getL1().getRepresentation();
                int v2 = masterVerticesToPrim.get(i).get(j).getL2().getRepresentation();
                double weight = masterVerticesToPrim.get(i).get(j).getWeight();
                Edge e = new Edge(v1, v2, weight);
                edgeWeightedGraph.addEdge(e);
            }
        }

        Prims prims = new Prims(edgeWeightedGraph);


        System.out.println("minimum spanning tree " + prims.mst);
        double weightOfMst = 0;
        for (Edge e : prims.mst) {
            weightOfMst = weightOfMst + e.getWeight();
        }
        System.out.println("weight of mst in km " + weightOfMst);

        HashMap<Integer, Integer> oddEvenVertices = FindOddVertices.getOddEvenVertices(prims.mst);

        ArrayList<Integer> oddVerticesArrayList = new ArrayList<>();
        oddVerticesArrayList = FindOddVertices.oddVertices(oddEvenVertices);


        List<Edge> pairs = PerfectMatching.PerfectMatchingPairs(edgeWeightedGraph, oddVerticesArrayList);
        System.out.println("perfect matching pairs " + pairs);
        System.out.println("number of pairs " + pairs.size());
        prims.mst.addAll(pairs);
        System.out.println("multigraph after adding pairs " + prims.mst);

        EulerianCycle eu = new EulerianCycle(prims.mst);
        Queue<Integer> eulerTour = eu.eulerianCycle();
        System.out.println("printing Eulerian tour " + eulerTour);
        List<Integer> hamiltonianCircuitPathList = eu.hamiltonianCircuitPath(eulerTour);
        System.out.println(" hamilton tour path " + hamiltonianCircuitPathList);
        double hamiltonianCircuitTourWeight = eu.hamiltonianCircuitTourWeight(hamiltonianCircuitPathList, masterVerticesToPrim);
        System.out.println(" hamilton tour path weight in km " + hamiltonianCircuitTourWeight);
        System.out.println(" hamilton tour path weight in metres " + hamiltonianCircuitTourWeight*1000);

        projectImpl obj = new projectImpl();
        HashMap<Integer, String> actualValues = new LinkedHashMap<>();
        actualValues = obj.convertingDataToArrayList();
        List<String> tourInHex = obj.representationOfTour(hamiltonianCircuitPathList, actualValues);
        System.out.println("tour in hex representation " + tourInHex);
        TwoOpt twoOptObject = new TwoOpt();
        System.out.println("inside twoOptCalculation method " + twoOptObject.twoOptCalculation(hamiltonianCircuitPathList, masterVerticesToPrim, hamiltonianCircuitTourWeight));
        ThreeOpt threeOptObject = new ThreeOpt();
        System.out.println("inside threeOptCalculation method " + threeOptObject.threeOptCalculation(hamiltonianCircuitPathList, masterVerticesToPrim, hamiltonianCircuitTourWeight));


    }
}