package com.esgi.al1.nearbymsg.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Process;
import android.support.annotation.NonNull;

import com.esgi.al1.nearbymsg.R;
import com.esgi.al1.nearbymsg.interfaces.Callable.AdvertisingDeviceListener;
import com.esgi.al1.nearbymsg.interfaces.Callable.DiscoveringDeviceListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AppIdentifier;
import com.google.android.gms.nearby.connection.AppMetadata;
import com.google.android.gms.nearby.connection.Connections.ConnectionRequestListener;
import com.google.android.gms.nearby.connection.Connections.EndpointDiscoveryListener;
import com.google.android.gms.nearby.connection.Connections.StartAdvertisingResult;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ACCOUNT_SERVICE;

/**
 * Created by Romaaan on 29/01/2017.
 */

public class NearbyService {

    public static boolean isConnectedToNetwork(Context ctx){
        ConnectivityManager connManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network allnetworks[] = connManager.getAllNetworks();
        for (Network nw : allnetworks){
            NetworkInfo info = connManager.getNetworkInfo(nw);
            if(info.isConnected())
                return true;
        }
        return false;
    }

    public static void startAdvertisingDevice(Context ctx, GoogleApiClient googleClient,
                                              ConnectionRequestListener rListener,
                                              final AdvertisingDeviceListener callback,
                                              String myName){

        // Advertising with an AppIdentifer lets other devices on the
        // network discover this application and prompt the user to
        // install the application.
        List<AppIdentifier> appIdentifierList = new ArrayList<>();
        appIdentifierList.add(new AppIdentifier(ctx.getPackageName()));
        AppMetadata appMetadata = new AppMetadata(appIdentifierList);

        // The advertising timeout is set to run indefinitely
        // Positive values represent timeout in milliseconds
        long TIMEOUT = 1000L * 30L;

        Nearby.Connections.startAdvertising(googleClient, myName, appMetadata, TIMEOUT,
                rListener).setResultCallback(new ResultCallback<StartAdvertisingResult>() {
            @Override
            public void onResult(@NonNull StartAdvertisingResult result) {
                //Calling back the activity with result object
                callback.onStartAdvertisingDevice(result);
            }
        });
    }

    public static void startDiscoveringDevices(String serviceId, GoogleApiClient googleClient, EndpointDiscoveryListener endPListener,
                                        final DiscoveringDeviceListener discListener){

        // Set an appropriate timeout length in milliseconds
        long DISCOVER_TIMEOUT = 1000L;
        // Discover nearby apps that are advertising with the required service ID.
        Nearby.Connections.startDiscovery(googleClient, serviceId, DISCOVER_TIMEOUT, endPListener)
            .setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    discListener.onStartDiscoveringDevice(status);
                }
            });
    }
    public static String getMyGoogleAccount (Activity act){
        if (act.checkPermission(ACCOUNT_SERVICE, Process.myPid(), 0) == PackageManager.PERMISSION_GRANTED) {
            AccountManager manager = (AccountManager) act.getSystemService(ACCOUNT_SERVICE);
            Account[] accs = manager.getAccounts();
            for (Account acc : accs){
                if (acc.type.equalsIgnoreCase("com.google"))
                    return acc.name;
            }
        }
        return null;
    }

}
