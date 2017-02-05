package com.esgi.al1.nearbymsg.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.esgi.al1.nearbymsg.entities.Device;
import com.esgi.al1.nearbymsg.entities.Discussion;

import java.sql.Connection;


/**
 * Created by Chris GAGOUDE on 28/01/2017.
 */

public class DiscussionDAO extends Dao<Discussion>{
    public DiscussionDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(Discussion obj, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(Discussion.ID, obj.getId());

        long newRowId = db.insert(Discussion.TABLE_NAME, null, values);
        Log.i("DATABASE", " Discussion inserted with id "+newRowId);
    }

    @Override
    public void update(Discussion obj, SQLiteDatabase db, long idInserted) {
        ContentValues values = new ContentValues();
        values.put(Discussion.ID, obj.getId());

        int idUpdate = db.update(Discussion.TABLE_NAME, values, Discussion.ID + "=?", new String[]{String.valueOf(idInserted)});
        Log.i("DATABASE_ACTIVITY", "Updated "+idUpdate+" rows");
    }

    @Override
    public void delete(Discussion obj, SQLiteDatabase db) {
        int idDelete = db.delete(Discussion.TABLE_NAME, Discussion.ID + "=?", new String[]{String.valueOf(obj.getId())});
        Log.i("DATABASE", "Deleted "+idDelete+" rows");
    }

    @Override
    public Discussion find(long id, SQLiteDatabase db) {
        Discussion discussion = new Discussion();
        String[] columns = {Discussion.ID};
        //Select * from Discussion where _IDDISCUSSION = id (passé en paramètre)
        Cursor cursor = db.query(Device.TABLE_NAME, columns, Discussion.ID+"=?", new String[]{String.valueOf(id)}, null, null, null);
        while(cursor.moveToNext()){
            long idCursor = cursor.getLong(cursor.getColumnIndex(Discussion.ID));

            //discussion = new Discussion((int)idCursor);
            //Log.i("DATABASE", "Retrieved "+discussion);
        }
        return discussion;
    }

}
