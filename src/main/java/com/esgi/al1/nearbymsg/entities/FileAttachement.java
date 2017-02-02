package com.esgi.al1.nearbymsg.entities;

/**
 * Created by Romaaan on 24/01/2017.
 */

public class FileAttachement {

    private String extension;
    private String name;
    private byte [] content;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        return String.valueOf(id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof FileAttachement && ((FileAttachement)obj).getId() == id);
    }
}
