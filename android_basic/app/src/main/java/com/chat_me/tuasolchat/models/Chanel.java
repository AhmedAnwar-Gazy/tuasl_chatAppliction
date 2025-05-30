package com.chat_me.tuasolchat.models;
import android.graphics.Bitmap;

import com.chat_me.tuasolchat.models.subModels.ChatItem;

import java.util.ArrayList;

public class Chanel implements ChatItem {

    private  String profilepicture;
    private  String bio;
    private  String name;
    private ArrayList<Member> members;
    private  String content;
    private  ArrayList<Message> messages;
    private String id;
    private int unreadMessagesCount;
    private Bitmap bitmapProfilePicture;

    public Chanel(String profilepicture, String bio, ArrayList<Member> members, String content, ArrayList<Message> messages) {
        this.profilepicture = profilepicture;
        this.bio = bio;
        this.members = members;
        this.content = content;
        this.messages = messages;
        this.bitmapProfilePicture = null;
        //TODO: change this default value
        this.unreadMessagesCount = 20;
    }

    public String getBio() {
        return bio;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public String getContent() {
        return content;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public void setContent(String content) {
        this.content = content;
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
    public String getName() {
        return this.name;
    }


    @Override
    public ArrayList<Message> getMessages() {
        return messages;
    }

    @Override
    public Status getStatus() {
        return Status.OFFLINE;
    }

    @Override
    public String getType() {
        return "channel";
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

    @Override
    public void setStatus(Status status) {

    }

    @Override
    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    @Override
    public void setName(String name) {this.name = name;}

    @Override
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @Override
    public void setBitmapProfilePicture(Bitmap picture) {
        this.bitmapProfilePicture = picture;
    }
    @Override
    public String getId() {
        return this.id;
    }
}
