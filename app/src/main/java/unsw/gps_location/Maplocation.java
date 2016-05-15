package unsw.gps_location;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class Maplocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maplocation);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ref_location = new LatLng(Distance_calculation.la_ref, Distance_calculation.lo_ref);
        mMap.addMarker(new MarkerOptions().position(ref_location).title("Reference Location"));
        mMap.addMarker(new MarkerOptions().position(ref_location).icon(BitmapDescriptorFactory.defaultMarker(0)));

        LatLng current_location=new LatLng(Start_location.la,Start_location.lo);
        mMap.addMarker(new MarkerOptions().position(current_location).title("Current Location"));
        mMap.addMarker(new MarkerOptions().position(current_location).icon(BitmapDescriptorFactory.defaultMarker(180)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ref_location, 10));
        //LatLng sydney = new LatLng(-33.86997, 151.2089);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
