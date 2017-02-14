package com.esgi.al1.nearbymsg.entities;

import java.util.Date;

/**
 * Created by Romaaan on 05/02/2017.
 */

public class Discussion {

    public static final String TABLE_NAME = "TABLE_DISCUSSION";
    public static final String ID = "ID";

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;


}
