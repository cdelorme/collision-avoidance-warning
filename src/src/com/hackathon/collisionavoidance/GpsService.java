package com.hackathon.collisionavoidance;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class GpsService extends Service {

    public void onCreate()
    {

        // Establish Gps Connection

    }

    public void onStartCommand()
    {

        // Begin Polling on 100ms intervals

        // Prepare BSM Packet

        // Emit GPS Data

    }

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}