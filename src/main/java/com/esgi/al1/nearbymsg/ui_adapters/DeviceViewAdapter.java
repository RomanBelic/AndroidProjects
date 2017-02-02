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
    private final int pagingSize;

    private int pageIndex;

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public DeviceViewAdapter(List<Device> lstDevice, Context context, int pagingSize) {
        super(lstDevice, context, R.layout.device_listview_layout);
        this.checkedArr = new SparseBooleanArray(lstDevice.size());
        this.pagingSize = pagingSize;
        this.pageIndex = 0;
    }

    public DeviceViewAdapter(List<Device> lstDevice, Context context) {
        super(lstDevice, context, R.layout.device_listview_layout);
        this.checkedArr = new SparseBooleanArray(lstDevice.size());
        this.pagingSize = this.pageIndex = 0;
    }

    @Override
    protected void initRow(Device device, View row, int position) {
        final int pagePos = pageIndex * pagingSize + position;
        TextView tvDevName = (TextView)row.findViewById(R.id.tvDeviceName);
        tvDevName.setText(device.getName());
        final CheckBox chk = (CheckBox)row.findViewById(R.id.chkDevice);
        chk.setChecked(checkedArr.get(pagePos, false));
        chk.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                if (chk.isChecked())
                    checkedArr.put(pagePos, true);
                else
                    checkedArr.delete(pagePos);
            }
        });
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public SparseBooleanArray getCheckedArr() {
        return checkedArr;
    }

    @Override
    public void notifyDataSetInvalidated() {
        checkedArr.clear();
        super.notifyDataSetInvalidated();
    }
}
