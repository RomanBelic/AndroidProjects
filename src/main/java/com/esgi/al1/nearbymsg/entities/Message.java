package com.esgi.al1.nearbymsg.entities;

import java.util.Date;

/**
 * Created by Romaaan on 24/01/2017.
 */

public class Message {
    public static final String TABLE_NAME = "MESSAGE";
    public static final String ID = "_IDMESSAGE";
    public static final String ID_DEVICE = "_IDDEVICE";
    public static final String ID_DISCUSSION = "DISCUSSION";
    public static final String BODY = "BODY";
    public static final String DATE_MESSAGE = "DATE_MESSAGE";

    private long id;
    private long idDevice;
    private long idDiscussion;
    private Date dateEnvoye;
    private String body;
    private boolean hasAttachement;
    private Device sender;

    public Message(){

    }

    public Message(long id, long idDevice, long idDiscussion, String body, Date today){
        this.id = id;
        this.idDevice = idDevice;
        this.idDiscussion = idDiscussion;
        this.body = body;
        this.dateEnvoye = today;
    }

    public FileAttachement getAttachement() {
        return attachement;
    }

    public void setAttachement(FileAttachement attachement) {
        this.attachement = attachement;
    }

    private FileAttachement attachement;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdDevice() {
        return idDevice;
    }

    public long getIdDiscussion() {
        return idDiscussion;
    }

    public void setIdDiscussion(long idDiscussion) {
        this.idDiscussion = idDiscussion;
    }

    public void setIdDevice(long idDevice) {
        this.idDevice = idDevice;
    }

    public Date getDateEnvoye() {
        return dateEnvoye;
    }

    public void setDateEnvoye(Date dateEnvoye) {
        this.dateEnvoye = dateEnvoye;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isHasAttachement() {
        return hasAttachement;
    }

    public void setHasAttachement(boolean hasAttachement) {
        this.hasAttachement = hasAttachement;
    }

    public Device getSender() {
        return sender;
    }

    public void setSender(Device sender) {
        this.sender = sender;
    }

    @Override
    public int hashCode() {
        return String.valueOf(id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Message && ((Message)obj).getId() == id);
    }
}
