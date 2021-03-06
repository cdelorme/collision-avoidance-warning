package com.hackathon.collisionavoidance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

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
	 * Executed on activity launch
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set Default States
		this.gpsServiceEnabled = false;
		this.wifiServiceEnabled = false;

		// Prepare Activity UI
        setContentView(R.layout.activity_main);
	}

    /**
     * Create Options Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void startActivityPed(View view) {
    	Intent i = new Intent(this, PedWarning.class);
    	startActivity(i);
    }
    
    public void startActivityCar(View view) {
    	Intent i = new Intent(this, CarWarning.class);
    	startActivity(i);
    }
//    
//    public void onToggleClicked(View view) {
//        // Is the toggle on?
//		// @todo add start button
//		// @todo add bindings to allow activity communication
//		/**
//		 *  Move these to start button click event
//		 */
//		Intent gpsIntent = new Intent(this, GpsService.class);
//		if (this.gpsServiceEnabled != true) {
//			startService(gpsIntent);
//			this.gpsServiceEnabled = true;
//		} else {
//			stopService(gpsIntent);
//			this.gpsServiceEnabled = false;
//		}
//
//		/**
//		 *  Move these to start button click event
//		 */
//		Intent wifiIntent = new Intent(this, WifiService.class);
//		if (this.wifiServiceEnabled != true) {
//			startService(wifiIntent);
//			this.wifiServiceEnabled = true;
//		} else {
//			stopService(wifiIntent);
//			this.wifiServiceEnabled = false;
//		}
//		// @todo logic to swap text/color to toggle start to stop button
//
//    }
}