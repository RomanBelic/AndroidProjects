package com.esgi.al1.nearbymsg.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.esgi.al1.nearbymsg.entities.Device;
import com.esgi.al1.nearbymsg.entities.Discussion;
import com.esgi.al1.nearbymsg.entities.Message;

/**
 * Created by Chris GAGOUDE on 28/01/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "chatdb";
    public static final int DB_VERSION = 1;

    private static final String DB_CREATE_DISCUSSION =
            "CREATE TABLE "+ Discussion.TABLE_NAME+" "+
                    "("+Discussion.ID+" INTEGER PRIMARY KEY AUTOINCREMENT"+")";

    private static final String DB_CREATE_DEVICE =
            "CREATE TABLE "+ Device.TABLE_NAME+" "+
                    "("+Device.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    Device.ACCOUNT_NAME+" VARCHAR, "+
                    Device.NAME+" VARCHAR"+
                    ")";

    private static final String DB_CREATE_MESSAGE =
            "CREATE TABLE "+ Message.TABLE_NAME+" "+
                    "("+Message.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    Message.ID_DEVICE+" INTEGER, "+
                    Message.ID_DISCUSSION+" INETEGER, "+
                    Message.BODY+" VARCHAR, "+
                    Message.DATE_MESSAGE+" DATETIME,"+
                    "FOREIGN KEY ("+Message.ID_DEVICE+") REFERENCES "+Device.TABLE_NAME+"("+Device.ID+"), "+
                    "FOREIGN KEY ("+Message.ID_DISCUSSION+") REFERENCES "+Discussion.TABLE_NAME+"("+Discussion.ID+")"+
                    ")";

    private static final String DROP_DEVICE = "DROP TABLE IF EXISTS "+Device.TABLE_NAME;
    private static final String DROP_DISCUSSION = "DROP TABLE IF EXISTS "+Discussion.TABLE_NAME;
    private static final String DROP_MESSAGE = "DROP TABLE IF EXISTS "+Message.TABLE_NAME;

    private DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_DEVICE);
        db.execSQL(DB_CREATE_DISCUSSION);
        db.execSQL(DB_CREATE_MESSAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_DEVICE);
        db.execSQL(DROP_DISCUSSION);
        db.execSQL(DROP_MESSAGE);
    }
}
