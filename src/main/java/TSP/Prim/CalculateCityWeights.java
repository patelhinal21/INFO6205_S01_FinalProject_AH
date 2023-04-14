package TSP.Prim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class CalculateCityWeights {

    private List<CityDetails> cityDetailsList;

    public CalculateCityWeights(List<CityDetails> cityDetailsList) {
        this.cityDetailsList = cityDetailsList;
    }

    public CalculateCityWeights() {
    }

    public HashMap<String, Double> cityWeights () {
        EulerianCycle ec = new EulerianCycle();
        int count = 0;
        List<Integer> cities = new ArrayList<>();
        for (int i = 0; i < 156; i++) {
            cities.add(i);
        }
        System.out.println("cities " + cities.size());
        HashMap<String, Double> cityDistance = new LinkedHashMap<>();
        for(int i = 0; i <cities.size()-1; i++)
        {
            for(int j= i+1; j < cities.size(); j++)
            {
//                System.out.println("i " + i + " j " + j);
                double dist = ec.getDistance(i,j, cityDetailsList);
//                System.out.println(dist);
                count = count + 1;
                cityDistance.put("" + i + "-" + "" + j, dist);

            }
        }

//        System.out.println("cities " + cities);
//        System.out.println("count of vertices in sample data set " + count);
//        System.out.println(cityDistance);
        return cityDistance;

    }

    public static void main(String[] args) {


        CalculateCityWeights obj = new CalculateCityWeights();
        obj.cityWeights();
    }
}
