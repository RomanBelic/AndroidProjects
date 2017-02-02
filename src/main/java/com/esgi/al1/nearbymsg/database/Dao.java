package com.esgi.al1.nearbymsg.database;

import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;

/**
 * Created by Chris GAGOUDE on 29/01/2017.
 */

public abstract class Dao <T> {
    protected Connection connection;

    public Dao(Connection connection){
        this.connection = connection;
    }

    public abstract void insert(T obj, SQLiteDatabase db);
    public abstract void update(T obj, SQLiteDatabase db, long idInserted);
    public abstract void delete(T obj, SQLiteDatabase db);
    public abstract T find(long id, SQLiteDatabase db);
}
