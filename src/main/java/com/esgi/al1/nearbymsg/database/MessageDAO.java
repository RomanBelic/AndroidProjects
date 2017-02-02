package com.esgi.al1.nearbymsg.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.esgi.al1.nearbymsg.entities.Message;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Chris GAGOUDE on 28/01/2017.
 */

public class MessageDAO extends Dao<Message>{

    public MessageDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(Message obj, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(Message.ID, obj.getId());
        values.put(Message.ID_DEVICE, obj.getIdDevice());
        values.put(Message.ID_DISCUSSION, obj.getIdDiscussion());
        values.put(Message.BODY, obj.getBody());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date today = Calendar.getInstance().getTime();
        String valueDate = formatter.format(today);

        values.put(Message.DATE_MESSAGE, valueDate);

        long newRowId = db.insert(Message.TABLE_NAME, null, values);
        Log.i("DATABASE", " Message inserted with id "+newRowId);
    }

    @Override
    public void update(Message obj, SQLiteDatabase db, long idInserted) {
        ContentValues values = new ContentValues();
        values.put(Message.ID, obj.getId());
        values.put(Message.ID_DEVICE, obj.getIdDevice());
        values.put(Message.ID_DISCUSSION, obj.getIdDiscussion());
        values.put(Message.BODY, obj.getBody());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateMessage = null;
        try {
            dateMessage = formatter.format(obj.getDateEnvoye());
        } catch (Exception e) {
            e.printStackTrace();
        }

        values.put(Message.DATE_MESSAGE, dateMessage);

        int idUpdate = db.update(Message.TABLE_NAME, values, Message.ID +"=?", new String[]{String.valueOf(obj.getId())});
        Log.i("DATABASE_ACTIVITY", "Updated "+idUpdate+" rows");
    }

    @Override
    public void delete(Message obj, SQLiteDatabase db) {
        int idDelete = db.delete(Message.TABLE_NAME, Message.ID + "=?", new String[]{String.valueOf(obj.getId())});
        Log.i("DATABASE", "Deleted "+idDelete+" rows");
    }

    @Override
    public Message find(long id, SQLiteDatabase db) {
        Message message = new Message();

        String[] columns = {Message.ID};

        Cursor cursor = db.query(Message.TABLE_NAME, columns, Message.ID+"=?", new String[]{String.valueOf(id)}, null, null, null);
        while (cursor.moveToNext()){
            long idCursor = cursor.getLong(cursor.getColumnIndex(Message.ID));
            long idDevice = cursor.getLong(cursor.getColumnIndex(Message.ID_DEVICE));
            long idDiscussion = cursor.getLong(cursor.getColumnIndex(Message.ID_DISCUSSION));
            String body = cursor.getString(cursor.getColumnIndex(Message.BODY));

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateMessage = null;
            try {
                dateMessage = format.parse(cursor.getString(cursor.getColumnIndex(Message.DATE_MESSAGE)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            message = new Message(idCursor, idDevice, idDiscussion, body, dateMessage);
            Log.i("DATABASE", "Retrieved "+message);
        }
        return message;
    }

}
