package com.hackathon.collisionavoidance;

import android.util.Log;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Menu;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener {

	/**
	 * Constant activity tag
	 */
	public static final String TAG = "MainActivity";

	/**
	 * Gps Service Thread
	 */
	protected GpsService gpsService;

	/**
	 * Wifi Service Thread
	 */
	protected WifiService wifiService;

	/**
	 * Identify State of GpsService
	 */
	protected boolean gpsServiceEnabled;

	/**
	 * Identify State of WifiService
	 */
	protected boolean wifiServiceEnabled;

	/**
	 * Text View (for Activity)
	 */
	protected TextView tv;

	/**
	 * ???
	 */
	protected Context mCtx;

	/**
	 * Executed on activity launch
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set Default States
		this.gpsServiceEnabled = false;
		this.wifiServiceEnabled = false;

		// @todo add start button
		// @todo add bindings to allow activity communication
		/**
		 *  Move these to start button click event
		 */
		Intent gpsIntent = new Intent(this, GpsService.class);
		if (this.gpsServiceEnabled != true) {
			startService(gpsIntent);
			this.gpsServiceEnabled = true;
		} else {
			stopService(gpsIntent);
			this.gpsServiceEnabled = false;
		}

		/**
		 *  Move these to start button click event
		 */
		Intent wifiIntent = new Intent(this, WifiService.class);
		if (this.wifiServiceEnabled != true) {
			startService(wifiIntent);
			this.wifiServiceEnabled = true;
		} else {
			stopService(wifiIntent);
			this.wifiServiceEnabled = false;
		}
		// @todo logic to swap text/color to toggle start to stop button
	}

	/**
	 * Catch provider disabled
	 */
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	/**
	 * Catch provider enabled
	 */
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	/**
	 * Catch status change
	 */
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}
}