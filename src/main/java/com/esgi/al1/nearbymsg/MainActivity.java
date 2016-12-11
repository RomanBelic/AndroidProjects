package com.esgi.al1.nearbymsg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.*;
import com.google.android.gms.nearby.Nearby;

public class MainActivity extends AppCompatActivity implements ConnectionCallbacks,OnConnectionFailedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Test du clsient
        Log.i("MyApp", "created");
        GoogleApiClient  mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Nearby.MESSAGES_API)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
        Log.i("MyApp", "connected");


    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("MyApp", "suspeded");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("MyApp", "failed");
    }
}
