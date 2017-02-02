package com.esgi.al1.nearbymsg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.esgi.al1.nearbymsg.entities.Device;
import com.esgi.al1.nearbymsg.interfaces.Callable.AdvertisingDeviceListener;
import com.esgi.al1.nearbymsg.interfaces.Callable.DeviceFragmentListener;
import com.esgi.al1.nearbymsg.interfaces.Callable.DiscoveringDeviceListener;
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
import com.google.android.gms.nearby.connection.Connections.ConnectionRequestListener;
import com.google.android.gms.nearby.connection.Connections.EndpointDiscoveryListener;
import com.google.android.gms.nearby.connection.Connections.MessageListener;
import com.google.android.gms.nearby.connection.Connections.StartAdvertisingResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private DeviceFragmentListener fragCallable;
    private NetworkFragmentListener netwfragCallable;
    private AdvertisingDeviceListener advListener;
    private DiscoveringDeviceListener discListener;
    private GoogleApiClient gClient;
    private OnConnectionFailedListener conFailedListener;
    private ConnectionCallbacks conCallBackListener;
    private ConnectionRequestListener conReqListener;
    private MessageListener mesgListener;
    private EndpointDiscoveryListener endPointListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInterfaces();
        initGoogleClient();

        boolean isConnected = NearbyService.isConnectedToNetwork(this);

        if (isConnected) {
            DeviceListFragment frag = DeviceListFragment.createNewInstance(getIntent().getExtras(), fragCallable);
            getFragmentManager().
            beginTransaction().
            replace(R.id.device_widgetview_container, frag, DeviceListFragment.TAG).
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
        fragCallable = new DeviceFragmentListener(){
            @Override
            public void onDevicesSelected(Bundle bundle) {
                Object result;
                if ((result = bundle.getSerializable(Device.Tag)) instanceof ArrayList<?>){
                    ArrayList<?> source = (ArrayList<?>) result;
                    if (source.size() > 0 && source.get(0) instanceof Device) {
                        ArrayList<Device> lstSelectedDevs = (ArrayList<Device>) source;
                        for(Device d : lstSelectedDevs){

                        }
                    }
                }
            }
        };

        netwfragCallable = new NetworkFragmentListener() {
            @Override
            public void onResult(int result) {
                if(result == RESULT_OK){
                    DeviceListFragment frag = DeviceListFragment.createNewInstance(getIntent().getExtras(), fragCallable);
                    getFragmentManager().
                    beginTransaction().
                    replace(R.id.device_widgetview_container, frag, DeviceListFragment.TAG).
                    commit();
                }
            }
        };

        advListener = new AdvertisingDeviceListener() {
            @Override
            public void onStartAdvertisingDevice(StartAdvertisingResult result) {

            }
        };

        discListener = new DiscoveringDeviceListener() {
            @Override
            public void onStartDiscoveringDevice(Status status) {

            }
        };

        conFailedListener = new OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

            }
        };

        conCallBackListener = new ConnectionCallbacks(){
            @Override
            public void onConnected(@Nullable Bundle bundle) {

            }

            @Override
            public void onConnectionSuspended(int i) {

            }
        };

        conReqListener = new ConnectionRequestListener() {
            @Override
            public void onConnectionRequest(String s, String s1, String s2, byte[] bytes) {

            }
        };

        endPointListener = new EndpointDiscoveryListener() {
            @Override
            public void onEndpointFound(String s, String s1, String s2, String s3) {

            }

            @Override
            public void onEndpointLost(String s) {

            }
        };

        mesgListener = new MessageListener() {
            @Override
            public void onMessageReceived(String s, byte[] bytes, boolean b) {

            }

            @Override
            public void onDisconnected(String s) {

            }
        };
    }

    private void initGoogleClient (){
        gClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(conCallBackListener)
                .addOnConnectionFailedListener(conFailedListener)
                .addApi(Nearby.CONNECTIONS_API)
                .enableAutoManage(this, conFailedListener)
                .build();
    }

}
