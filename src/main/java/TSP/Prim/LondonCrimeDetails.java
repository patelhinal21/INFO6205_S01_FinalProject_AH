package TSP.Prim;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LondonCrimeDetails {
    private String crimeID;
    private double longitude;
    private double latitude;


    public LondonCrimeDetails(String crimeID, double longitude, double latitude) {
        this.crimeID = crimeID;
        this.longitude = longitude;
        this.latitude = latitude;

    }

    public String getCrimeID() {
        return crimeID;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }



    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<LondonCrimeDetails> crimes = new ArrayList<>();

        String csvFile = "/Users/hinalpatel/Desktop/Final_Project_PSA_S01_AH/src/main/java/TSP/Prim/info6205.spring2023.teamproject.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);

                LondonCrimeDetails crime = new LondonCrimeDetails(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]));
                crimes.add(crime);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Do something with the list of crimes
         //...
//        Scanner sc = new Scanner (new File("/Users/hinalpatel/Desktop/Final_Project_PSA_S01_AH/src/main/java/TSP/Prim/info6205.spring2023.teamproject.csv"));
//        sc.useDelimiter(",");   //sets the delimiter pattern
//        while (sc.hasNext())  //returns a boolean value
//        {
//            System.out.print(sc.next());  //find and returns the next complete token from this scanner
//        }
//        sc.close();  //closes the scanner

    }
}


