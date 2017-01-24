package com.esgi.al1.nearbymsg.entities;

/**
 * Created by Romaaan on 24/01/2017.
 */

public class FileAttachement {

    private String extension;
    private String name;
    private byte [] content;

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
}
