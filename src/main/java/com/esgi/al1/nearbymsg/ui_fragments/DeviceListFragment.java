package com.esgi.al1.nearbymsg.ui_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.esgi.al1.nearbymsg.R;
import com.esgi.al1.nearbymsg.entities.Device;
import com.esgi.al1.nearbymsg.interfaces.Callable.CallableByFragment;
import com.esgi.al1.nearbymsg.ui_adapters.DeviceViewAdapter;
import com.esgi.al1.nearbymsg.ui_widgets.DeviceListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romaaan on 23/01/2017.
 */

public class DeviceListFragment extends Fragment {

    public static final String TAG = "DeviceListFrag";
    public static final int Id = 0x99;
    private CallableByFragment callable;

    public static DeviceListFragment createNewInstance(Bundle args, CallableByFragment callable){
        DeviceListFragment frag = new DeviceListFragment();
        frag.setArguments(args);
        frag.callable = callable;
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ui = inflater.inflate(R.layout.device_widgetview_layout, container, false);
        initComponents (ui);
        return ui;
    }

    private void initComponents (final View ui){
        final Button btnStartDisc = (Button)ui.findViewById(R.id.btnStartDiscussion);
        btnStartDisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartDiscPressed(btnStartDisc, ui);
            }}
        );
        List<Device> lstDevices = new ArrayList<Device>(64);
        DeviceListView lvDevices = (DeviceListView)ui.findViewById(R.id.lstV_AvailableDevices);
        for (int i = 0; i< 64;i++){
            lstDevices.add(new Device("id" + i,"name" + i));
        }
        DeviceViewAdapter adapter = new DeviceViewAdapter(lstDevices, ui.getContext());
        lvDevices.setAdapter(adapter);
    }

    private void onStartDiscPressed(Button sender, View root){
        DeviceListView lvDevices = (DeviceListView)root.findViewById(R.id.lstV_AvailableDevices);
        DeviceViewAdapter adapter = (DeviceViewAdapter) lvDevices.getAdapter();
        ArrayList<Device> lstChecked = new ArrayList<>(64);

        for(int i = 0; i < adapter.getCheckedArr().size(); i++) {
            Device d = adapter.getSource().get(i);
            lstChecked.add(d);
        }
        if (lstChecked.size() > 0) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Device.Tag,lstChecked);
            this.callable.onDevicesSelected(bundle);
        }
    }

}
