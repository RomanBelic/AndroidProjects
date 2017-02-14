package com.esgi.al1.nearbymsg.ui_fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.esgi.al1.nearbymsg.R;
import com.esgi.al1.nearbymsg.interfaces.Callable.NetworkFragmentListener;
import com.esgi.al1.nearbymsg.utils.NearbyService;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Romaaan on 30/01/2017.
 */

public class ActivateNetworkFragment extends Fragment {

    public static final String TAG = "ActivateNetworkFrag";
    public static final int Id = 0x88;
    private NetworkFragmentListener callable;
    private final int reqCode = 0x01;

    public static ActivateNetworkFragment createNewInstance(Bundle args, NetworkFragmentListener callable){
        ActivateNetworkFragment frag = new ActivateNetworkFragment();
        frag.setArguments(args);
        frag.callable = callable;
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ui = inflater.inflate(R.layout.network_dialog_layout, container, false);
        final Button btnOpenSettings = (Button)ui.findViewById(R.id.btnCheckNetwork);
        btnOpenSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                startActivityForResult(intent, reqCode);
            }
        });
        return ui;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode == reqCode){
           int result = NearbyService.isConnectedToNetwork(getActivity()) ? RESULT_OK : RESULT_CANCELED;
           callable.onResult(result);
       }
    }
}
