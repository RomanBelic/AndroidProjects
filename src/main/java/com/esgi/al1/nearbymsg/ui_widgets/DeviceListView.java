package com.esgi.al1.nearbymsg.ui_widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.esgi.al1.nearbymsg.entities.Device;
import com.esgi.al1.nearbymsg.ui_adapters.DeviceViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romaaan on 23/01/2017.
 */

public class DeviceListView extends ListView {

    private void init(Context context){
        List<Device> lstData = new ArrayList<Device> (5){
            {
                add(new Device(0,"toto1@dupon.fr","Toto1"));
                add(new Device(0,"toto2@dupon.fr","Toto2"));
                add(new Device(0,"toto3@dupon.fr","Toto3"));
                add(new Device(0,"toto4@dupon.fr","Toto4"));
                add(new Device(0,"toto5@dupon.fr","Toto5"));
            }
        };

        DeviceViewAdapter adapter = new DeviceViewAdapter(lstData, context);
        this.setAdapter(adapter);
    }

    public DeviceListView(Context context) {
        super(context);
        init(context);
    }

    public DeviceListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DeviceListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public DeviceListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
}
