package com.esgi.al1.nearbymsg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.esgi.al1.nearbymsg.entities.Device;
import com.esgi.al1.nearbymsg.interfaces.Callable.CallableByFragment;
import com.esgi.al1.nearbymsg.ui_fragments.DeviceListFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private CallableByFragment callable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInterfaces();

        DeviceListFragment frag = DeviceListFragment.createNewInstance(getIntent().getExtras(), callable);
        getFragmentManager().
                beginTransaction().
                replace(R.id.device_widgetview_container, frag, DeviceListFragment.TAG).
                commit();

    }

    public void loadDiscussionActivity(Bundle bundle) {
        Object result;
        if ((result = bundle.getSerializable(Device.Tag)) instanceof ArrayList<?>){
            ArrayList<?> source = (ArrayList<?>) result;
            if (source.size() > 0 && source.get(0) instanceof Device) {
                ArrayList<Device> lstSelectedDevs = (ArrayList<Device>) source;
                Toast.makeText(this, "Selected " + lstSelectedDevs.size(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void initInterfaces(){
        callable = new CallableByFragment(){
            @Override
            public void onDevicesSelected(Bundle bundle) {
                loadDiscussionActivity(bundle);
            }
        };
    }
}
