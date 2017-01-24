package com.esgi.al1.nearbymsg.ui_widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.esgi.al1.nearbymsg.R;

/**
 * Created by Romaaan on 23/01/2017.
 */

public class DeviceWidgetLayout extends LinearLayout{

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.device_widgetview_layout, this, true);
    }

    public DeviceWidgetLayout(Context context) {
        super(context);
        init(context);
    }

    public DeviceWidgetLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DeviceWidgetLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public DeviceWidgetLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
}
