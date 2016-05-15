package unsw.gps_location;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Start_location extends AppCompatActivity {

    public Button set_ref;
    public Button googlemap;
    public Button set_ref_google;
    public Button get_current_location;
    public Button get_distance;
    public EditText Lo;
    public EditText La;
    public TextView lo_la_ref;
    public TextView current_location;
    public TextView distance;


    public static double la;
    public static double lo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_location);

        set_ref=(Button)findViewById(R.id.set_ref);
        googlemap=(Button)findViewById(R.id.googlemap);
        set_ref_google=(Button)findViewById(R.id.set_location_google);
        get_current_location=(Button)findViewById(R.id.get_current_location);
        get_distance=(Button)findViewById(R.id.get_distance);
        Lo=(EditText)findViewById(R.id.edit_Lo);
        La=(EditText)findViewById(R.id.edit_La);
        lo_la_ref=(TextView)findViewById(R.id.lo_la_ref);
        current_location=(TextView)findViewById(R.id.current_location);
        distance=(TextView)findViewById(R.id.distance);


       set_ref.setOnClickListener(new View.OnClickListener()
       {
            public void onClick(View v)
            {
                if (La.getText().toString().equals("") || Lo.getText().toString().equals(""))
                {
                    if (Distance_calculation.la_ref==0 || Distance_calculation.lo_ref==0)
                    {
                        lo_la_ref.setText("Please set a valid reference location!");
                    }
                    else
                    {
                        lo_la_ref.setText("Reference Location" + "\nLatitude : " + Distance_calculation.la_ref + "\nLongitude : " + Distance_calculation.lo_ref);
                    }
                }

                else
                {
                    Distance_calculation.lo_ref=Double.parseDouble(Lo.getText().toString());
                    Distance_calculation.la_ref=Double.parseDouble(La.getText().toString());
                    lo_la_ref.setText("Reference Location" + "\nLatitude : " + Distance_calculation.la_ref + "\nLongitude : " + Distance_calculation.lo_ref);
                }
            }
       });

        set_ref_google.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent intent_begin=new Intent(Start_location.this,Maplocation_set.class);
                startActivityForResult(intent_begin,0);
            }
        });

        googlemap.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
            Intent intent_begin=new Intent(Start_location.this,Maplocation.class);
                startActivity(intent_begin);
            }
        });

        get_current_location.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                current_location.setText("Current Location\n"+location());
            }
        });

        get_distance.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                distance.setText("Distance\n"+Distance_calculation.getDistance(Distance_calculation.lo_ref, Distance_calculation.la_ref, lo, la));
            }
        });
    }


    final LocationListener locationListener = new LocationListener()
    {
        public void onLocationChanged(Location location) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {}
        public void onProviderEnabled(String provider) {}
        public void onProviderDisabled(String provider) {}
    };

    public String location()
    {
        String La_Lo=null;
        final LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        try
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        } catch (SecurityException e){}
        try
        {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location==null)
            {
                location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            lo=location.getLongitude();
            la=location.getLatitude();
            La_Lo="Latitude : "+Double.toString(location.getLatitude()) + "\n"+"Longitude : "+Double.toString(location.getLongitude());
        }catch (SecurityException e){}
        return La_Lo;
    }

}
