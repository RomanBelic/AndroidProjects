package com.esgi.al1.nearbymsg.entities;

import java.util.Date;

/**
 * Created by Romaaan on 24/01/2017.
 */

public class Message {

    private int id;
    private Date dateEnvoye;
    private String body;
    private boolean hasAttachement;
    private Device sender;

    public FileAttachement getAttachement() {
        return attachement;
    }

    public void setAttachement(FileAttachement attachement) {
        this.attachement = attachement;
    }

    private FileAttachement attachement;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
