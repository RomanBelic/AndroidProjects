package com.esgi.al1.nearbymsg;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.esgi.al1.nearbymsg.broadcast_receiver.MessageReceiver;
import com.esgi.al1.nearbymsg.entities.Device;
import com.esgi.al1.nearbymsg.interfaces.Callable.DiscoveringDeviceListener;
import com.esgi.al1.nearbymsg.interfaces.Callable.AdvertisingDeviceListener;
import com.esgi.al1.nearbymsg.interfaces.Callable.DeviceFragmentListener;
import com.esgi.al1.nearbymsg.interfaces.Callable.NetworkFragmentListener;
import com.esgi.al1.nearbymsg.ui_fragments.ActivateNetworkFragment;
import com.esgi.al1.nearbymsg.ui_fragments.DeviceListFragment;
import com.esgi.al1.nearbymsg.utils.NearbyService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.Connections.EndpointDiscoveryListener;
import com.google.android.gms.nearby.connection.Connections.ConnectionRequestListener;
import com.google.android.gms.nearby.connection.Connections.MessageListener;
import com.google.android.gms.nearby.connection.Connections.StartAdvertisingResult;

public class MainActivity extends AppCompatActivity implements
        ConnectionCallbacks,
        OnConnectionFailedListener,
        ConnectionRequestListener,
        MessageListener,
        EndpointDiscoveryListener
{
    private DeviceFragmentListener fragCallable;
    private NetworkFragmentListener netwfragCallable;
    private GoogleApiClient gClient;
    private AdvertisingDeviceListener advListener;
    private DiscoveringDeviceListener discListener;
    private boolean isConnectedNetwork = false;
    private boolean isHost = false;
    private DeviceListFragment deviceFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInterfaces();
        isConnectedNetwork = NearbyService.isConnectedToNetwork(this);

        if (isConnectedNetwork) {
            gClient = initGoogleApiClient(this, this, this);
            deviceFrag = DeviceListFragment.createNewInstance(getIntent().getExtras(), fragCallable);
            getFragmentManager().
            beginTransaction().
            replace(R.id.device_widgetview_container, deviceFrag, DeviceListFragment.TAG).
            commit();
        } else {
            ActivateNetworkFragment frag = ActivateNetworkFragment.createNewInstance(getIntent().getExtras(), netwfragCallable);
            getFragmentManager().
            beginTransaction().
            replace(R.id.network_check_container, frag, ActivateNetworkFragment.TAG).
            commit();
        }
    }

    @SuppressWarnings("unchecked")
    private void initInterfaces(){
        netwfragCallable = new NetworkFragmentListener() {
            @Override
            public void onResult(int result) {
                if(result == RESULT_OK){
                    deviceFrag = DeviceListFragment.createNewInstance(getIntent().getExtras(), fragCallable);
                    getFragmentManager().
                    beginTransaction().
                    replace(R.id.device_widgetview_container, deviceFrag, DeviceListFragment.TAG).
                    commit();
                }
            }
        };

        advListener = new AdvertisingDeviceListener() {
            @Override
            public void onStartAdvertisingDevice(StartAdvertisingResult result) {

            }
        };

        discListener = new DiscoveringDeviceListener (){
            @Override
            public void onStartDiscoveringDevice(Status status) {

            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private GoogleApiClient initGoogleApiClient (OnConnectionFailedListener failListener, ConnectionCallbacks callback, Context ctx){
        return new GoogleApiClient.Builder(ctx)
                .addConnectionCallbacks(callback)
                .addOnConnectionFailedListener(failListener)
                .addApi(Nearby.CONNECTIONS_API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        gClient.reconnect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnectionRequest(final String remoteEndpointId, String remoteDeviceId,
                                    String remoteEndpointName, byte[] payload) {

    }

    @Override
    public void onMessageReceived(String endpointId, byte[] data, boolean isReliable) {
        Intent intent = new Intent();
        String msg = "";
        for (byte b : data){
            msg += (char)b;
        }
        intent.putExtra("message", msg);
     //   intent.setAction(getString(R.string.On_Message_Received));
        sendBroadcast(intent);
    }

    @Override
    public void onDisconnected(String s) {

    }

    @Override
    public void onEndpointFound(String endpointId, String deviceId, String serviceId, String name) {
        if (serviceId.equals(getString(R.string.service_id))) {
            Device d = new Device(0, deviceId, name);
            if (deviceFrag != null)
                deviceFrag.onDeviceFound(d);
        }
    }

    @Override
    public void onEndpointLost(String s) {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getAction().equals(getString(R.string.service_id))){//WIDGET_PRESS_ACTION
            Intent response = new Intent(getString(R.string.service_id));//WIDGET_RESPONSE_PRESSED
            if (gClient != null && gClient.isConnected()) {
                gClient.disconnect();
                response.putExtra("button_update", getString(R.string.service_id));//strON
                Toast.makeText(this, "Disconnected", Toast.LENGTH_LONG).show();
            }
            else if (gClient != null && !gClient.isConnected()) {
                gClient.connect();
                response.putExtra("button_update", getString(R.string.service_id));//strOFF
                Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show();
            }
            DeviceAppWidget.UpdateForced(this, response);
        }
        super.onNewIntent(intent);
    }

}
