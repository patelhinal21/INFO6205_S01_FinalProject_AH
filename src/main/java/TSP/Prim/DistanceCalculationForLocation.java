package TSP.Prim;

public class DistanceCalculationForLocation {

    public DistanceCalculationForLocation() {
    }

    public double distanceBetweenTwoLocations(double lat1, double lat2, double lng1, double lng2) {
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
}
