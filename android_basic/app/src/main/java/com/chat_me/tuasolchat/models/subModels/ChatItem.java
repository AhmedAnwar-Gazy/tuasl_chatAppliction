package com.chat_me.tuasolchat.models.subModels;

import android.graphics.Bitmap;

import com.chat_me.tuasolchat.models.Message;
import com.chat_me.tuasolchat.models.Status;

import java.util.ArrayList;

public interface ChatItem {
    public String getProfilepicture();
    public Bitmap getBitmapProfilepicture();
    public String getName();
    public ArrayList<Message> getMessages();
    public Status getStatus();
    public String getType();
    public int getUnreadMessagesCount();

    public void setUnreadMessagesCount(int count);
    public void setType(String type);
    public void setStatus(Status status);
    public void setProfilepicture(String profilepicture) ;
    public void setName(String bio);
    public void setMessages(ArrayList<Message> messages);
    public void setBitmapProfilePicture(Bitmap picture);

    public String getId();
}
