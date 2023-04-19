package TSP.Prim;

import java.util.ArrayList;
import java.util.List;

public class LondoncrimeDetailsEdge {

    private LondonCrimeDetails l1;
    private LondonCrimeDetails l2;
    private double weight;

    public LondoncrimeDetailsEdge() {
    }

    public LondoncrimeDetailsEdge(LondonCrimeDetails l1, LondonCrimeDetails l2, double weight) {
        this.l1 = l1;
        this.l2 = l2;
        this.weight = weight;
    }

    public LondonCrimeDetails getL1() {
        return l1;
    }

    public void setL1(LondonCrimeDetails l1) {
        this.l1 = l1;
    }

    public LondonCrimeDetails getL2() {
        return l2;
    }

    public void setL2(LondonCrimeDetails l2) {
        this.l2 = l2;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<List<LondoncrimeDetailsEdge>> getEdgeInfo () {
        List<List<LondoncrimeDetailsEdge>> masterListOfVertices = new ArrayList<>();


        LondonCrimeDetails londonCrimeDetails = new LondonCrimeDetails();
        ArrayList<LondonCrimeDetails> dataSetValues = londonCrimeDetails.convertingDataToArrayList();

        for(int i =0; i < 584; i++)
        {
            List<LondoncrimeDetailsEdge> listPerVertex = new ArrayList<>();

            for(int j =0; j < 584; j++) {
                if (i != j) {
                    LondonCrimeDetails lcd1 = dataSetValues.get(i);
                    LondonCrimeDetails lcd2 = dataSetValues.get(j);

                    double lat1 = lcd1.getLatitude();
                    double long1 = lcd1.getLongitude();

                    double lat2 = lcd2.getLatitude();
                    double long2 = lcd2.getLongitude();

                    double weight = distanceUsingFormula(lat1, lat2, long1, long2);

                    LondoncrimeDetailsEdge edge = new LondoncrimeDetailsEdge(lcd1, lcd2, weight);
                    listPerVertex.add(edge);
                }
            }
            masterListOfVertices.add(listPerVertex);
        }
        return masterListOfVertices;
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

    @Override
    public String toString() {
        return "LondoncrimeDetailsEdge{" +
                "l1=" + l1 +
                ", l2=" + l2 +
                ", weight=" + weight +
                '}';
    }

    public static void main(String[] args) {
        LondoncrimeDetailsEdge edge = new LondoncrimeDetailsEdge();
        System.out.println("inside crime details edge "+ edge.getEdgeInfo().size());
        System.out.println("sample value " + edge.getEdgeInfo().get(441).get(291));
    }
}
