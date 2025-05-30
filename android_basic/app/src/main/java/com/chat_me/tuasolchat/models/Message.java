package com.chat_me.tuasolchat.models;


import java.util.Date;

public class Message
{
    private  String sender;
    private String recipient;
    private  byte [] text;
    private  Type type;
    private  String extention;
    private byte [] media;
    private Date dateSent;

    public Message(String sender, String recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }

    public Message(String sender, byte[] media, String extention, Type type, byte[] text, String recipient) {
        this.sender = sender;
        this.media = media;
        this.extention = extention;
        this.type = type;
        this.text = text;
        this.recipient = recipient;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    public Message() {
    }


    public Message(String sender, String recipient, byte[] text, Type type, String extention) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
        this.type = type;
        this.extention = extention;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public Message(String sender, String recipient, byte[] text, Type type, String extention, byte[] media, Date dateSent) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
        this.type = type;
        this.extention = extention;
        this.media = media;
        this.dateSent = dateSent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        this.text = text;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }
}