package com.chat_me.tuasolchat.models;


import java.util.ArrayList;

public class User {

    private  String username;
    private  String bio;
    private  String fullname;
    private  String email;
    private  String profilepicture;
    private  Status status ;
    public static User user;

    private ArrayList<Chanel> chanels = new ArrayList<Chanel>();

    public User() {

    }

    public User(String username, String bio, String fullname, String email, String profilepicture, Status status, ArrayList<Chanel> chanels) {
        this.username = username;
        this.bio = bio;
        this.fullname = fullname;
        this.email = email;
        this.profilepicture = profilepicture;

        this.chanels = chanels;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Chanel> getChamnels() {
        return chanels;
    }

    public void setChamnels(ArrayList<Chanel> chanels) {
        this.chanels = chanels;
    }
}
