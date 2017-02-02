package com.esgi.al1.nearbymsg.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.esgi.al1.nearbymsg.entities.Device;

import java.sql.Connection;


/**
 * Created by Chris GAGOUDE on 28/01/2017.
 */

public class DeviceDAO extends Dao<Device>{

    public DeviceDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(Device obj, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(obj.ID, obj.getId());
        values.put(obj.ACCOUNT_NAME, obj.getAccount());
        values.put(obj.NAME, obj.getName());

        long newRowId = db.insert(obj.TABLE_NAME, null, values);
        Log.i("DATABASE", " Device inserted with id "+newRowId);
    }

    @Override
    public void update(Device obj, SQLiteDatabase db, long idInserted) {
        ContentValues values = new ContentValues();
        values.put(Device.ID, obj.getId());
        values.put(Device.ACCOUNT_NAME, obj.getAccount());
        values.put(Device.NAME, obj.getName());

        int idUpdate = db.update(Device.TABLE_NAME, values, Device.ID + "=?", new String[]{String.valueOf(idInserted)});
        Log.i("DATABASE_ACTIVITY", "Updated "+idUpdate+" rows");
    }

    @Override
    public void delete(Device obj, SQLiteDatabase db) {
        int idDelete = db.delete(Device.TABLE_NAME, Device.ID + "=?", new String[]{String.valueOf(obj.getId())});
        Log.i("DATABASE", "Deleted "+idDelete+" rows");
    }

    @Override
    public Device find(long id, SQLiteDatabase db) {
        Device device = new Device();
        //Cursor cursor = db.query(Device.TABLE_NAME, null, null, null, null, null, null);    // Select * from Device
        String [] columns = {Device.ID};
        Cursor cursor = db.query(Device.TABLE_NAME, columns, Device.ID+"=?", new String[]{String.valueOf(id)}, null, null, null);     //Select * from Device where idDevice = id passé en paramètre
        while(cursor.moveToNext()){
            long idCursor = cursor.getLong(cursor.getColumnIndex(Device.ID));
            String account_name = cursor.getString(cursor.getColumnIndex(Device.ACCOUNT_NAME));
            String name = cursor.getString(cursor.getColumnIndex(Device.NAME));

            device = new Device(idCursor, account_name, name);
            Log.i("DATABASE", "Retrieved "+device);
        }
        return device;
    }
}
