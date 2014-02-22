package com.hackathon.collisionavoidance;

import java.util.ArrayList;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;

/**
 * Wifi Broadcast Receiver
 *
 * This tool is the interface to WifiDirect
 *
 * @author Casey DeLorme <cdelorme@gmail.com>
 * @date 2014-2-21
 * @link http://developer.android.com/guide/topics/connectivity/wifip2p.html
 */
public class WifiBroadcastReceiver extends BroadcastReceiver implements PeerListListener {

    /**
     * Wifi P2p Manager Instance
     */
    protected WifiP2pManager wifiP2pManager;

    /**
     * Wifi P2p Manager Channel
     */
    protected Channel wifiP2pChannel;

    /**
     * List of peers
     */
    protected List peers = new ArrayList<WifiP2pDevice>();

    /**
     * Constructor
     *
     * @param WifiP2pManager manager
     * @param WifiP2pManager.Channel channel
     */
    public WifiBroadcastReceiver(WifiP2pManager manager, Channel channel)
    {
        super();
        this.wifiP2pManager = manager;
        this.wifiP2pChannel = channel;
    }

    /**
     * Handle asynchronous actions
     *
     * @param Context context of the action
     * @param Intent intent describing the action
     */
    @Override
    public void onReceive(Context context, Intent intent)
    {

        // Check action
        String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {

            // Check state of wifi
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state != WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                // Notify user their wifi is disabled
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

            // Request peers and connect to PeerListListener
            this.wifiP2pManager.requestPeers(this.wifiP2pChannel, this);
        }
    }

    /**
     * Acquire new peers list and connect
     *
     * @param WifiP2pDeviceList peerList of devices!
     */
    @Override
    public void onPeersAvailable(WifiP2pDeviceList peerList)
    {
        // Wipe old list
        // @todo filter connected peers (by address) out of new list
        this.peers.clear();

        // Add new list
        this.peers.addAll(peerList.getDeviceList());

        // Iterate to connect
        for (WifiP2pDevice device : this.peers) {

            // Prepare Config /w Device Address
            WifiP2pConfig wifiP2pConfig = new WifiP2pConfig();
            wifiP2pConfig.deviceAddress = device.deviceAddress;

            // Connect
            // @todo add success/failure handling
            this.wifiP2pManager.connect(this.wifiP2pChannel, config, null);
        }
    }
}