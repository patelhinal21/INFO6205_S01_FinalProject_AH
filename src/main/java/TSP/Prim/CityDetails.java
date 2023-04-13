package TSP.Prim;

public class CityDetails {

    private final int a;
    private final double lat;
    private final double lng;


    public CityDetails(int a, double lng, double lat) {
        this.a = a;
        this.lat = lat;
        this.lng = lng;
    }

    public int getA() {
        return a;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return '{' + "a=" + a + ", lat=" + lat + ", lng=" + lng + '}';
    }
}
