package com.hackathon.collisionavoidance;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener {

	private static final String TAG = "MainActivity";
	private TextView tv;
	LocationManager lm;
	Context mCtx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		boolean gps_enabled = false;
		boolean network_enabled = false;

		LocationManager lm = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//
//		Log.d(TAG, "here");
//		gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
//		network_enabled = lm
//				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//
//		Log.d(TAG, "here2");
//
//		Location net_loc = null, gps_loc = null, finalLoc = null;
//
//		if (gps_enabled) {
//			gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//			Log.d(TAG, "here4");
//		}
//		if (network_enabled) {
//			net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//			Log.d(TAG, "here5");
//		}
//
//		if (gps_loc != null && net_loc != null) {
//
//			if (gps_loc.getAccuracy() >= net_loc.getAccuracy())
//				finalLoc = gps_loc;
//			else
//				finalLoc = net_loc;
//
//			// I used this just to get an idea (if both avail, its upto you
//			// which you want to take as I taken location with more accuracy)
//		} else {
//
//			if (gps_loc != null) {
//				finalLoc = net_loc;
//			} else if (net_loc != null) {
//				finalLoc = gps_loc;
//			}
//		}
//		Log.d(TAG, "here7");
//		if (finalLoc != null) {
//			Log.d(TAG, finalLoc.toString());
//		}
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		int lat = (int) (location.getLatitude());
		int lng = (int) (location.getLongitude());
		int t = (int)(location.getTime());
		float s;
		if ( location.hasSpeed() ) {
			 s = (location.getSpeed());
		} else s = 10;
		
		String disp = "lat:" + lat + " long:" + lng + " time:" + t + " speed:" + s;
		Log.d(TAG, disp);
		Log.d(TAG, location.toString());

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
