package com.esgi.al1.nearbymsg.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
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
                                       ConnectionRequestListener rListener, final AdvertisingDeviceListener callback){

        // Advertising with an AppIdentifer lets other devices on the
        // network discover this application and prompt the user to
        // install the application.
        List<AppIdentifier> appIdentifierList = new ArrayList<>();
        appIdentifierList.add(new AppIdentifier(ctx.getPackageName()));
        AppMetadata appMetadata = new AppMetadata(appIdentifierList);

        // The advertising timeout is set to run indefinitely
        // Positive values represent timeout in milliseconds
        long NO_TIMEOUT = 0L;

        String serviceId = ctx.getString(R.string.service_id);

        Nearby.Connections.startAdvertising(googleClient, serviceId, appMetadata, NO_TIMEOUT,
                rListener).setResultCallback(new ResultCallback<StartAdvertisingResult>() {
            @Override
            public void onResult(@NonNull StartAdvertisingResult result) {
                //Calling back the activity with result object
                callback.onStartAdvertisingDevice(result);
            }
        });
    }

    public static void startDiscoveringDevices(Context ctx, GoogleApiClient googleClient, EndpointDiscoveryListener discListener,
                                        final DiscoveringDeviceListener callback){
        String myDeviceID = null;

        // Set an appropriate timeout length in milliseconds
        long DISCOVER_TIMEOUT = 1000L;

        // Discover nearby apps that are advertising with the required service ID.
        Nearby.Connections.startDiscovery(googleClient, myDeviceID, DISCOVER_TIMEOUT, discListener)
            .setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    callback.onStartDiscoveringDevice(status);
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
