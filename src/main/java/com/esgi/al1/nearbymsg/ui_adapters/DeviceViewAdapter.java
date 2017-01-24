package com.esgi.al1.nearbymsg.ui_adapters;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.esgi.al1.nearbymsg.R;
import com.esgi.al1.nearbymsg.entities.Device;

import java.util.List;

/**
 * Created by Romaaan on 23/01/2017.
 */

public class DeviceViewAdapter extends GenericAdapter<Device> {

    private final SparseBooleanArray checkedArr;
    private Context cont;
    public DeviceViewAdapter(List<Device> lstDevice, Context context) {
        super(lstDevice, context, R.layout.device_listview_layout);
        checkedArr = new SparseBooleanArray(lstDevice.size());
        cont = context;
    }

    @Override
    protected void initRow(Device device, View row, final int position) {
        TextView tvDevName = (TextView)row.findViewById(R.id.tvDeviceName);
        tvDevName.setText(device.getName());
        final CheckBox chk = (CheckBox)row.findViewById(R.id.chkDevice);
        chk.setChecked(checkedArr.get(position, false));
        chk.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                if (chk.isChecked())
                    checkedArr.put(position, true);
            }
        });
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        checkedArr.clear();
    }

    public SparseBooleanArray getCheckedArr() {
        return checkedArr;
    }

    @Override
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        checkedArr.clear();
    }
}
