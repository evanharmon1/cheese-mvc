package org.launchcode.models;

import java.util.Date;

public class User {

    private int userId;
    private static int nextId = 1;
    private String username;
    private String email;
    private String password;
    private Date joined;

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    public User() {
        userId = nextId;
        nextId++;
        joined = new Date();
    }
}
