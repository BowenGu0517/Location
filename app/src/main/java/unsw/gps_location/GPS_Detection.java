package unsw.gps_location;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GPS_Detection extends Service {

    public static double la;
    public static double lo;
    public static double la_ref=-33;
    public static double lo_ref=151;
    public static double distance;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public  void onCreate() {
        openGPSSettings();

        String serviceName = Context.LOCATION_SERVICE;

        LocationManager locationManager = (LocationManager)this.getSystemService(serviceName);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
        catch (SecurityException e){
        }

        distance=Distance_calculation.getDistance(lo_ref, la_ref, lo, la);
        Intent intent=new Intent();
        intent.putExtra("distance_GPS", distance);
        Toast.makeText(getApplicationContext(),"GOT distance",Toast.LENGTH_LONG).show();
    }

    private void openGPSSettings()
    {
        LocationManager status = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        if (status.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER))
        {
            return;
        }
        else
        {
            try {

                Intent open_GPS = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                GPS_Detection.this.startActivity(open_GPS);
            }
            catch (SecurityException e) {
            }

            Toast.makeText(getApplicationContext(),"GPS module opened",Toast.LENGTH_SHORT).show();
            return;
        }

    }
    final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
           la=location.getLatitude();
           lo=location.getLongitude();
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {}
        public void onProviderEnabled(String provider) {}
        public void onProviderDisabled(String provider) {}
    };

}



