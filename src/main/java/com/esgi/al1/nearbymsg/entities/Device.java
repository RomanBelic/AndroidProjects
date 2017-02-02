package com.esgi.al1.nearbymsg.entities;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by Romaaan on 23/01/2017.
 */

public class Device implements Serializable {
    public static final String TABLE_NAME="DEVICE";
    public static final String ID = "_IDDEVICE";
    public static final String ACCOUNT_NAME = "ACCOUNT_NAME";
    public static final String NAME = "NAME";

    public static final String Tag = "Device";

    private String account;
    private String name;
    private long id;

    public Device (){

    }

    public Device(long id, String newAccount, String newName) {
        this.id = id;
        this.account = newAccount;
        this.name = newName;
    }

    protected Device(Parcel in) {
        account = in.readString();
        name = in.readString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return account.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Device && ((Device)obj).getAccount().equals(account));
    }
}
