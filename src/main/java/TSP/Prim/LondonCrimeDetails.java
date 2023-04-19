package TSP.Prim;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LondonCrimeDetails {
    private String crimeID;
    private double longitude;
    private double latitude;
    private int representation;

    public LondonCrimeDetails(String crimeID, double longitude, double latitude, int representation) {
        this.crimeID = crimeID;
        this.longitude = longitude;
        this.latitude = latitude;
        this.representation = representation;
    }

    public LondonCrimeDetails() {
    }

    public ArrayList<LondonCrimeDetails> sampleArrayList = new ArrayList<>();

    public String getCrimeID() {
        return crimeID;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getRepresentation() {
        return representation;
    }

    public void setCrimeID(String crimeID) {
        this.crimeID = crimeID;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setRepresentation(int representation) {
        this.representation = representation;
    }

    public ArrayList<LondonCrimeDetails> convertingDataToArrayList() {
        String line = "";
        String csvSplitBy = ",";

        File fileObj = new File("/Users/aishwaryavenkatesan/Desktop/programming struct algo/INFO6205_S01_FinalProject_AH/src/main/java/TSP/Prim/psasampledatafinal.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(fileObj))) {
            while ((line = br.readLine()) != null) {
                String data[] = line.split(csvSplitBy);

                String crimeId = data[0];
                double lon = Double.parseDouble(data[1]);
                double lan = Double.parseDouble(data[2]);
                int repId = Integer.parseInt(data[3]);

                LondonCrimeDetails crimeDetails = new LondonCrimeDetails(crimeId, lon, lan, repId);
                sampleArrayList.add(crimeDetails);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sampleArrayList;
    }


    @Override
    public String toString() {
        return "LondonCrimeDetails{" +
                "crimeID='" + crimeID + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", representation=" + representation +
                '}';
    }

}


