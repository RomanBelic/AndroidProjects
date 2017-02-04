package com.esgi.al1.nearbymsg.ui_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.esgi.al1.nearbymsg.R;
import com.esgi.al1.nearbymsg.entities.Device;
import com.esgi.al1.nearbymsg.interfaces.Callable.DeviceFragmentListener;
import com.esgi.al1.nearbymsg.interfaces.Callable.DiscoveringDeviceListener;
import com.esgi.al1.nearbymsg.ui_adapters.DeviceViewAdapter;
import com.esgi.al1.nearbymsg.ui_widgets.DeviceListView;
import com.esgi.al1.nearbymsg.utils.NearbyService;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.Connections.EndpointDiscoveryListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romaaan on 23/01/2017.
 */

public class DeviceListFragment extends Fragment implements DeviceFragmentListener {

    public static final String TAG = "DeviceListFrag";
    public static final int Id = 0x99;
    private int pageCount;
    private int pageSize;
    private List<Device> lstDevice;
    private DeviceViewAdapter adapter;
    private DeviceFragmentListener fragCallable;

    public static DeviceListFragment createNewInstance(Bundle extras, DeviceFragmentListener fragCallable){
        DeviceListFragment frag = new DeviceListFragment();
        frag.setArguments(extras);
        frag.fragCallable = fragCallable;
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
        pageCount = 0;
        pageSize = 5;
        lstDevice = new ArrayList<>(64);
        for (int i = 0; i < 64;i++){
            lstDevice.add(new Device("i" + i, "i" + i));
        }
        DeviceListView lvDevices = (DeviceListView) ui.findViewById(R.id.lstV_AvailableDevices);
        adapter = new DeviceViewAdapter(lstDevice, getActivity(), pageSize);
        lvDevices.setAdapter(adapter);

        Button btnStartDisc = (Button)ui.findViewById(R.id.btnStartDiscussion);
        btnStartDisc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Device> lstChecked = new ArrayList<>(64);
                for(int i = 0; i < adapter.getCheckedArr().size(); i++) {
                    int itemIndex = adapter.getCheckedArr().keyAt(i);
                    Device d = lstDevice.get(itemIndex);
                    lstChecked.add(d);
                }
                if (lstChecked.size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Device.Tag,lstChecked);



                }
            }}
        );

        Button btnNext = (Button) ui.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               int pageLim = lstDevice.size() / pageSize - 1;
               if (pageCount < pageLim) {
                   pageCount ++;
                   adapter.setPageIndex(pageCount);
                   int startPos = pageCount * pageSize;
                   int endPos = startPos + pageSize;
                   List<Device> lstTemp = new ArrayList<>(lstDevice.size());
                   for (; startPos < endPos; startPos++) {
                       if ((lstDevice.size() - startPos) == 0)
                           break;
                       Device d = lstDevice.get(startPos);
                       lstTemp.add(d);
                   }
                   adapter.setLstSource(lstTemp);
                   adapter.notifyDataSetChanged();
               }
           }
        });

        Button btnPrev = (Button) ui.findViewById(R.id.btnPrevious);
        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageCount > 0) {
                    pageCount --;
                    adapter.setPageIndex(pageCount);
                    int startPos = pageCount * pageSize;
                    int endPos = startPos + pageSize;
                    List<Device> lstTemp = new ArrayList<>(lstDevice.size());
                    for (; startPos < endPos; startPos++) {
                        Device d = lstDevice.get(startPos);
                        lstTemp.add(d);
                    }
                    adapter.setLstSource(lstTemp);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        Button btnRefr = (Button)ui.findViewById(R.id.btnRefresh);
        btnRefr.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = 0;
                adapter.setPageIndex(0);
                adapter.getCheckedArr().clear();
                List<Device> tmpDevice = new ArrayList<>(pageSize);
                for (int i = 0; i < pageSize; i++){
                    tmpDevice.add(lstDevice.get(i));
                }
                adapter.setLstSource(tmpDevice);
                adapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public void onDeviceFound(Device device) {
        lstDevice.add(device);
        adapter.notifyDataSetChanged();
    }
}
