package unsw.gps_location;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class Conditions extends Service {
    public Conditions() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {


        @Override
        public void onReceive(Context context, Intent intent) {
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (info.isConnected())
            {
                Intent intent_WIFI=new Intent(Conditions.this,WIFI_Detection.class);
                startService(intent_WIFI);
                Toast.makeText(getApplicationContext(),"WIFI location Service begin",Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent intent_GPS=new Intent(Conditions.this,GPS_Detection.class);
                startService(intent_GPS);
                Toast.makeText(getApplicationContext(),"GPS location Service begin",Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onCreate()
    {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }
}