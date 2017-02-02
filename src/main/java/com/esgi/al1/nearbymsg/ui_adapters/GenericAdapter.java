package com.esgi.al1.nearbymsg.ui_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Romaaan on 23/01/2017.
 */

public class GenericAdapter<T> extends BaseAdapter {

    private List<T> lstSource;
    private final int layoutId;
    private final LayoutInflater inflater;

    GenericAdapter(List<T> lstDevice, Context context, int layoutId){
        this.lstSource = lstDevice;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutId = layoutId;
    }

    public T getElement (int position){
        return lstSource.get(position);
    }

    @Override
    public int getCount() {
        return lstSource.size();
    }

    @Override
    public Object getItem(int position) {
        return lstSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    void initRow(T object, View row, int position){}

    public List<T> getLstSource() {
        return lstSource;
    }

    public void setLstSource(List<T> lstSource) {
        this.lstSource = lstSource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if (convertView == null)
           convertView = this.inflater.inflate(layoutId, null);

           T item = lstSource.get(position);
           initRow(item, convertView, position);

       return convertView;
    }
}
