package unsw.gps_location;

/**
 * Created by Administrator on 2016/5/3.
 */
public class Distance_calculation {

    public static double EARTH_RADIUS= 6378137.0;
    public static double la_ref;
    public static double lo_ref;


    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2)
    {
        double Lat1 = rad(latitude1);
        double Lat2 = rad(latitude2);
        double a = Lat1 - Lat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(Lat1) * Math.cos(Lat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
}
