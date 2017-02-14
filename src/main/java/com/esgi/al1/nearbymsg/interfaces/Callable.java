package com.esgi.al1.nearbymsg.interfaces;

import android.os.Bundle;

import com.esgi.al1.nearbymsg.entities.Device;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.Connections.StartAdvertisingResult;

/**
 * Created by Romaaan on 23/01/2017.
 */

public interface Callable {
     interface DeviceFragmentListener {
         void onDeviceFound (Device device);
     }
    interface AdvertisingDeviceListener {
        void onStartAdvertisingDevice(StartAdvertisingResult result);
    }

    interface DiscoveringDeviceListener {
        void onStartDiscoveringDevice(Status status);
    }

    interface NetworkFragmentListener {
        void onResult(int result);
    }
}
