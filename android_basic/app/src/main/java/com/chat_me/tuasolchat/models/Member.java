package com.chat_me.tuasolchat.models;
import android.graphics.Bitmap;

import com.chat_me.tuasolchat.models.Status;
import com.chat_me.tuasolchat.models.subModels.ChatItem;

import java.util.ArrayList;
import java.util.Date;

public class Member implements ChatItem {
    private String id;
    private  String name;
    private  String profilepicture;
    private  String rule;
    private Status status;
    private ArrayList<Message> messages;
    private Bitmap bitmapProfilePicture;
    private int unreadMessagesCount;

    public Member(
            String id,
            String name, 
            String profilepicture, 
            String rule, 
            Status status
    ) {
        this.id = id;
        this.name = name;
        this.profilepicture = profilepicture;
        this.rule = rule;
        this.status = status;
        this.messages = new ArrayList<>();
        messages.add(new Message("1", "2", "byte[] text".getBytes(), Type.FILE, "extention", null, new Date("12/12/2023")));// , Type type, String extention, byte[] media, Date dateSent)
    }
    public Member(String name, String profilepicture, String rule, Status status) {
        this.name = name;
        this.profilepicture = profilepicture;
        this.rule = rule;

    }

    public Member() {
    }

    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<Message> getMessages() {return this.messages;}

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @Override
    public void setBitmapProfilePicture(Bitmap picture) {
        this.bitmapProfilePicture = picture;
    }

    @Override
    public String getProfilepicture() {
        return profilepicture;
    }

    @Override
    public Bitmap getBitmapProfilepicture() {
       return this.bitmapProfilePicture;
    }

    @Override
    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String getType() {
        return "member";
    }

    @Override
    public int getUnreadMessagesCount() {
       return this.unreadMessagesCount;
    }

    @Override
    public void setUnreadMessagesCount(int count) {
        this.unreadMessagesCount = count;
    }

    @Override
    public void setType(String type) {
    }


}
