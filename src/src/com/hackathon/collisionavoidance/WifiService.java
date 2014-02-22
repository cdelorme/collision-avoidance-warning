package com.hackathon.collisionavoidance;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.IBinder;

/**
 * Background Wifi Service
 *
 * This is the service that prepares a p2p connection and
 * transmits gps data using BSM (Basic Safety Message) Packets
 *
 * @author Casey DeLorme <cdelorme@gmail.com>
 * @date 2014-2-21
 * @link http://developer.android.com/guide/topics/connectivity/wifip2p.html
 */
public class WifiService extends Service {

    /**
     * Broadcast Receiver Instance
     */
    protected WifiBroadcastReceiver wifiBroadcastReceiver;

    /**
     * Wifi P2p Manager Instance
     */
    protected WifiP2pManager wifiP2pManager;

    /**
     * Wifi P2p Manager Channel
     */
    protected Channel wifiP2pChannel;

    /**
     * Automatically executed on service creation
     */
    public void onCreate()
    {

        // Create new Wifi P2p Manager
        this.wifiP2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);

        // Initialize Wifi P2p Manager to acquire channel
        this.wifiP2pChannel = wifiP2pManager.initialize(this, getMainLooper(), null);

        // Create wifi direct broadcast tool
        this.wifiBroadcastReceiver = new WifiBroadcastReceiver(this.wifiP2pManager, this.wifiP2pChannel);

        // Create intentFilter to add listeners
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        // intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        // intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        intentFilter.addAction(GpsService.TRANSMIT_GPS_DATA);

        // Register Receiver /w Filter
        registerReceiver(this.wifiBroadcastReceiver, intentFilter);

        // Look for peers
        // @todo how often does the system actively look for peers, and can we adjust that interval?
        // @todo add success/failure handling
        this.wifiP2pManager.discoverPeers(this.wifiP2pChannel, null);
    }

    /**
     * Run on `start()`
     */
    public void onStartCommand()
    {
        // ???
        // Perhaps we tell the GpsService to begin polling now?

        // Add listener to the GpsService to handle poll data
    }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}