package com.esgi.al1.nearbymsg.entities;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by Romaaan on 23/01/2017.
 */

public class Device implements Serializable {

    public static final String Tag = "Device";

    private String account;
    private String name;

    public Device (){

    }

    public Device(String newAccount, String newName) {
        this.account = newAccount;
        this.name = newName;
    }

    protected Device(Parcel in) {
        account = in.readString();
        name = in.readString();
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
